#pragma once
#include"PointGeneration.h"
template <class T> void SafeRelease(T **ppT)
{
	if (*ppT)
	{
		(*ppT)->Release();
		*ppT = NULL;
	}
}

class MainWindow : public BaseWindow<MainWindow>
{
	ID2D1Factory            *pFactory;
	ID2D1HwndRenderTarget   *pRenderTarget;
	ID2D1SolidColorBrush    *pBrush;
	D2D1_ROUNDED_RECT		r_rect;
	Units					*head;
	Units					*tail;
	float					height, length;

	void    CalculateLayout();
	HRESULT CreateGraphicsResources();
	void    DiscardGraphicsResources();
	void    OnPaint();
	void    Resize();

public:
	PointGeneration * p_g;
	MainWindow() : pFactory(NULL), pRenderTarget(NULL), pBrush(NULL)
	{
		head = new Units(15, 15, Direction::right);
		tail = head;
	}
	HWND GetHandle();
	Units* GetUnit();
	bool DataOperation(Direction, PointGeneration&);
	bool	AddUnits(Units);
	int CheckCrash(PointGeneration &p_g);
	PCWSTR  ClassName() const { return L"Circle Window Class"; }
	LRESULT HandleMessage(UINT uMsg, WPARAM wParam, LPARAM lParam);
};