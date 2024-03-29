// WindowsProject1.cpp : Defines the entry point for the application.
//

#include "stdafx.h"
#include "WindowsProject1.h"




#include <windows.h>
#pragma comment(lib, "d2d1")

//Providing for background thread.
struct myData
{
	MainWindow *m_w;
	PointGeneration *p_g;
};
DWORD WINAPI recycleDraw(LPVOID lpParam);
int WINAPI wWinMain(HINSTANCE hInstance, HINSTANCE, PWSTR, int nCmdShow)
{
	MainWindow win;
	PointGeneration po_gen;
	win.p_g = &po_gen;
	DWORD handle;
	myData data;
	data.m_w = &win;
	data.p_g = &po_gen;
	if (!win.Create(L"SnakeGame", WS_OVERLAPPEDWINDOW))
	{
		return 0;
	}

	ShowWindow(win.Window(), nCmdShow);
	//SetTimer(win.GetHandle(), 100, 200, (TIMERPROC)NULL);
	CreateThread(NULL, 0, recycleDraw, &data, 0, &handle);
	// Run the message loop.
	MSG msg = {};
	while (GetMessage(&msg, NULL, 0, 0))
	{
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}

	return 0;
}

DWORD WINAPI recycleDraw(LPVOID lpParam)
{
	myData *data = (myData*)lpParam;	//an error occurs
	while (TRUE)
	{
		data->m_w->DataOperation(data->m_w->GetUnit()->dir, *(data->p_g));
		InvalidateRect(data->m_w->GetHandle(), NULL, false);
		Sleep(200);
	}
	return 0;
}
