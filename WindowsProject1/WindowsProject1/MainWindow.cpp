#include"stdafx.h"
// Recalculate drawing layout when the size of the window changes.

#define GLOBAL_SPEED 30;
void MainWindow::CalculateLayout()
{
	if (pRenderTarget != NULL)
	{
		D2D1_SIZE_F size = pRenderTarget->GetSize();
		this->length = size.width;
		this->height = size.height;
	}
}

HRESULT MainWindow::CreateGraphicsResources()
{
	HRESULT hr = S_OK;
	if (pRenderTarget == NULL)
	{
		RECT rc;
		GetClientRect(m_hwnd, &rc);

		D2D1_SIZE_U size = D2D1::SizeU(rc.right, rc.bottom);

		hr = pFactory->CreateHwndRenderTarget(
			D2D1::RenderTargetProperties(), //default parameter
			D2D1::HwndRenderTargetProperties(m_hwnd, size),
			&pRenderTarget);

		if (SUCCEEDED(hr))
		{
			const D2D1_COLOR_F color = D2D1::ColorF(1.0f, 1.0f, 0);
			hr = pRenderTarget->CreateSolidColorBrush(color, &pBrush);

			if (SUCCEEDED(hr))
			{
				CalculateLayout();
			}
		}
	}
	return hr;
}

void MainWindow::DiscardGraphicsResources()
{
	SafeRelease(&pRenderTarget);
	SafeRelease(&pBrush);
}

void MainWindow::OnPaint()
{
	HRESULT hr = CreateGraphicsResources();
	if (SUCCEEDED(hr))
	{
		PAINTSTRUCT ps;
		BeginPaint(m_hwnd, &ps);
		pRenderTarget->BeginDraw();

		pRenderTarget->Clear(D2D1::ColorF(D2D1::ColorF::SkyBlue));
		Units *temp = this->head;
		while (temp)
		{
			pRenderTarget->FillRoundedRectangle(temp->r_rect, pBrush);
			temp = temp->next;
		}
		pRenderTarget->FillEllipse(p_g->ellipse, pBrush);
		hr = pRenderTarget->EndDraw();
		if (FAILED(hr) || hr == D2DERR_RECREATE_TARGET)
		{
			DiscardGraphicsResources();
		}
		EndPaint(m_hwnd, &ps);
	}
}

void MainWindow::Resize()
{
	if (pRenderTarget != NULL)
	{
		RECT rc;
		GetClientRect(m_hwnd, &rc);

		D2D1_SIZE_U size = D2D1::SizeU(rc.right, rc.bottom);

		pRenderTarget->Resize(size);
		CalculateLayout();
		InvalidateRect(m_hwnd, NULL, FALSE);
	}
}
HWND MainWindow::GetHandle()
{
	return m_hwnd;
}
Units* MainWindow::GetUnit()
{
	return head;
}
/*
*Only add data.Not about drawing.
*
*/
bool MainWindow::AddUnits(Units unit)
{
	
	Units **temp = &head;
	while (*temp)
	{
		(*temp)->RectChange();
		//attention: the address is the pointer's not its content.
		temp = &((*temp)->next);
	}
		*temp = new Units();
		if (*temp)
		{
			**temp = unit;
			(*temp)->previous = tail;
			tail = *temp;
			return TRUE;
		}
		return FALSE;
}
/*
*operating data according to the game characteristic.
*/
bool MainWindow::DataOperation(Direction dir, PointGeneration &p_g)
{
	Units tail_pre = *tail;
	 //It is useful to add one to tail.
	if (tail->previous)
	{
		//Can not use double level pointer.Because it will be impossible to change 
		//head or tail separately.
		Units *temp = tail->previous;
		Units *_tail = tail;
		//delete tail
		temp->next = NULL;
		tail->previous = NULL;
		tail = temp;
	
		*_tail = *head;
		head->previous = _tail;
		_tail->next = head;
		head = _tail;
	}
	if (dir == Direction::up)
	{
		head->center_location.y -= GLOBAL_SPEED;
	}
	else if (dir == Direction::down)
	{
		head->center_location.y += GLOBAL_SPEED;
	}
	else if (dir == Direction::left)
	{
		head->center_location.x -= GLOBAL_SPEED;
	}
	else if (dir == Direction::right)
	{
		head->center_location.x += GLOBAL_SPEED;
	}
	//If it is marked as comment. wonnt move.
	//update rect
	//This is the main cause of the 'const length'.
	Units *temp = this->head;
	while (temp)
	{
		temp->RectChange();
		temp = temp->next;
	}
	int tt = CheckCrash(p_g);
	if (tt == 1)
	{
		system("pause");
	}
	if (tt == 2)
	{
		AddUnits(tail_pre);
	}
	return FALSE;
}
int MainWindow::CheckCrash(PointGeneration &p_g)
{
	float x_loc = head->center_location.x;
	float y_loc = head->center_location.y;
	//boundary 
	//attention when the length will get value. 
	bool a;
	if (head->center_location.x == p_g.loc.x && head->center_location.y == p_g.loc.y)
	{
		//system("pause");
		a = p_g.Generation(this->height, this->length);
		return 2; //eat point.
	}
	if (x_loc > this->length - 15 || x_loc < 0 || y_loc < 0 || y_loc > height - 15)
	{
		return 1; //crash into the wall.
	}
	//crash its body.
	Units *temp = this->head;
	while (temp->next)
	{
		if (head->center_location.x == temp->next->center_location.x && head->center_location.y == temp->next->center_location.y)
		{
			return 1; //crash into its body.
		}
		//I think the best way to adjust the crash is that 
		//get their area.Using API to adjust.
		//But a pity, I didn't find it.
		temp = temp->next;
	}
	
	return FALSE; //nothing happen!
}


LRESULT MainWindow::HandleMessage(UINT uMsg, WPARAM wParam, LPARAM lParam)
{
	switch (uMsg)
	{
	case WM_CREATE:
		if (FAILED(D2D1CreateFactory(
			D2D1_FACTORY_TYPE_SINGLE_THREADED, &pFactory)))
		{
			return -1;  // Fail CreateWindowEx.
		}
		RECT rc;
		GetClientRect(m_hwnd, &rc);
		this->height = rc.bottom - rc.top;
		this->length = rc.right - rc.left;
		//SetTimer(m_hwnd, 100, 500, (TIMERPROC)NULL);
		return 0;
	case WM_KEYDOWN:
		switch (wParam)
		{
		case 87://w
			if (head->dir == Direction::down)
			{
				return FALSE;
			}
			head->dir = Direction::up;
			break;
		case 65://a
			if (head->dir == Direction::right)
			{
				return FALSE;
			}
			head->dir = Direction::left;
			break;
		case 83://s
			if (head->dir == Direction::up)
			{
				return FALSE;
			}
			head->dir = Direction::down;
			break;
		case 68://d
			if (head->dir == Direction::left)
			{
				return FALSE;
			}
			head->dir = Direction::right;
			break;
		}
		break;
	case WM_TIMER:
		//boundary edge
		//crash the wall
		switch (wParam)
		{
		case 100:
			//DataOperation(head->dir);
			InvalidateRect(m_hwnd, NULL, false);
			return 0;
		}
	case WM_DESTROY:
		DiscardGraphicsResources();
		SafeRelease(&pFactory);
		PostQuitMessage(0);
		return 0;

	case WM_PAINT:
		OnPaint();
		return 0;
	case WM_SIZE:
		Resize();
		return 0;
	}
	return DefWindowProc(m_hwnd, uMsg, wParam, lParam);
}