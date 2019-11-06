#pragma once
class PointGeneration
{
public:
	D2D1_POINT_2F loc;
	D2D1_ELLIPSE            ellipse;
	PointGeneration();
	bool Generation(float height, float length);
};