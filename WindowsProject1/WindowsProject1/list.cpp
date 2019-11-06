#include"stdafx.h"
/*
*First, i am not necessary to write a templete. After completing this simplified version, then take it into consideration.
*
class snakeUnit {
public:
	snakeUnit(long x, long y, ID2D1HwndRenderTarget   *pRenderTarget, ID2D1SolidColorBrush    *pBrush, ID2D1SolidColorBrush    *pClearBrush);
	~snakeUnit() {}

private:
	int drawUnit();
	int deleteUnit(ID2D1SolidColorBrush    *pBrush);
	POINT location;
	static SIZE size;
	D2D1_ROUNDED_RECT		r_rect;	//necessary for 'FillRoundRectangle'
	D2D1_RECT_F				rect_f; //necessary for 'RoundedRect'
	static ID2D1HwndRenderTarget   *pRenderTarget;
	static ID2D1SolidColorBrush    *pBrush;
	static ID2D1SolidColorBrush	   *pClearBrush;	//delete a round rectangle.
};
snakeUnit::snakeUnit(long x, long y, ID2D1HwndRenderTarget   *pRenderTarget = nullptr, ID2D1SolidColorBrush    *pBrush = nullptr, ID2D1SolidColorBrush    *pClearBrush)
{
	location.x = x;
	location.y = y;
	if (pRenderTarget && pBrush && pClearBrush)
	{
		snakeUnit::pRenderTarget = pRenderTarget;
		snakeUnit::pBrush = pBrush;
		snakeUnit::pClearBrush = pClearBrush;
	}
	rect_f = D2D1::RectF(location.x - size.cx, location.y - size.cy, location.x + size.cx, location.y + size.cy);
	r_rect = D2D1::RoundedRect(rect_f, size.cx / 10, size.cy / 10);
}
//rectangle brush
int snakeUnit::drawUnit()
{
	pRenderTarget->FillRoundedRectangle(r_rect, pBrush);
}
//pBrush background brush
int snakeUnit::deleteUnit(ID2D1SolidColorBrush    *pClearBrush)
{
	pRenderTarget->FillRoundedRectangle(r_rect, pClearBrush);
}
*/