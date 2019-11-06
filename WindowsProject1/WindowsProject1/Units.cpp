#include"stdafx.h"
#define UNIT_RADIUS 30

Units::Units()
{
	D2D1_RECT_F	rect_f = D2D1::RectF(0, 0, 0, 0);
	this->r_rect = D2D1::RoundedRect(rect_f, 0, 0);
	this->center_location.x = 0;
	this->center_location.y = 0;
	this->dir = Direction(null);
	this->next = NULL;
	this->previous = NULL;
}

Units::Units(float x, float y, Direction dir)
{
	this->center_location.x = x;
	this->center_location.y = y;
	this->dir = dir;
	D2D1_RECT_F	rect_f = D2D1::RectF(x - UNIT_RADIUS/2, y - UNIT_RADIUS/2, 
		x + UNIT_RADIUS/2, y + UNIT_RADIUS/2);
	this->r_rect = D2D1::RoundedRect(rect_f, 10, 10);
	this->next = NULL;
	this->previous = NULL;
}

Units& Units::operator=(Units &unit)
{
	this->center_location = unit.center_location;
	this->dir = unit.dir;
	return *this;
}

bool Units::operator== (const Units& unit)
{
	if (this->center_location.x == unit.center_location.x && this->center_location.y == unit.center_location.y)
	{
		return TRUE;
	}
	return FALSE;
}
bool Units::RectChange()
{
	D2D1_RECT_F	rect_f = D2D1::RectF(this->center_location.x - UNIT_RADIUS / 2, this->center_location.y - UNIT_RADIUS / 2,
		this->center_location.x + UNIT_RADIUS / 2, this->center_location.y + UNIT_RADIUS / 2);
	this->r_rect = D2D1::RoundedRect(rect_f, 10, 10);
	return TRUE;
}

bool Units::UnitAdd(Units *unit)
{
	if (unit)
	{
		this->next = unit;
		return TRUE;
	}
	return FALSE;
}