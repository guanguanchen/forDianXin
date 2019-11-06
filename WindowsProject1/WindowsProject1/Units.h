#pragma once

#ifndef _UNITS_H
#define _UNITS_H
	enum Direction { null, up, down, left, right };
	class Units
	{
	public:
		D2D1_ROUNDED_RECT		r_rect;	//24 bytes
		D2D1_POINT_2F center_location;		//8
		Direction dir;					//4
		Units *next;					//4
		Units *previous;


		Units();
		Units(float, float, Direction);
		Units& operator=(Units &);
		bool operator== (const Units &);

		bool RectChange();
		bool UnitAdd(Units *unit);
	};
	/*
	*if define these constructors outer the class, and the stdafx.h includes it, then the linker will give
	*error LNK2005.But i don't know why.
	*
	*/
	//Units::Units()
	//{
	//
	//}
	/**
	Units& Units::operator=(Units &unit)
	{
	this->center_location = unit.center_location;
	this->dir = unit.dir;
	return *this;
	}*
	
	Units::Units(long x, long y, Direction dir)
	{
		this->center_location.x = x;
		this->center_location.y = y;
		this->dir = dir;
		D2D1_RECT_F	rect_f = D2D1::RectF(x - 50, y - 50, x + 50, y + 50);
		this->r_rect = D2D1::RoundedRect(rect_f, 15, 15);
	}
	*/
#endif