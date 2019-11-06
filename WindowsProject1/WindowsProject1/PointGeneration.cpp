#include"stdafx.h"
//The client's boundary
PointGeneration::PointGeneration()
{
	this->loc.x = 75;
	this->loc.y = 15;
	this->ellipse = D2D1::Ellipse(D2D1::Point2F(this->loc.x, this->loc.y), 30/2, 30/2);
}
//Builting a sequence containing the food location.
bool PointGeneration::Generation(float h = 0, float l = 0)
{
	unsigned _h = 0, _l = 0;
	unsigned n_h = (h - 15) / 30, n_l = (l - 15) / 30;
	srand((unsigned)time(NULL));

	_h = rand() % n_h;
	_l = rand() % n_l;

	this->loc.x = _l * 30 + 15;
	this->loc.y = _h * 30 + 15;
	this->ellipse = D2D1::Ellipse(D2D1::Point2F(this->loc.x, this->loc.y), 30 / 2, 30 / 2);
	return TRUE;
}