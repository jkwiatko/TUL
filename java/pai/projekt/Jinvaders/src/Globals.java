package Jinvaders;

import jcurses.system.CharColor;

class Globals{
	final static short 		GAME_LOOP 		= 1;
	final static short 		GAME_NEXTLEVEL 	= 2;
	final static short 		GAME_PAUSED  	= 3;
	final static short 		GAME_OVER 		= 4;
	final static short 		GAME_EXIT 		= 5;
	final static short 		GAME_HIGHSCORE 	= 6;
	final static short 		PLAYERWIDTH 	= 6;
	final static CharColor 	RED 			= new CharColor(CharColor.BLACK, CharColor.RED);
	final static CharColor 	GREEN 			= new CharColor(CharColor.BLACK, CharColor.GREEN);
	final static CharColor 	YELLOW 			= new CharColor(CharColor.BLACK, CharColor.YELLOW);
	final static CharColor 	BLUE 			= new CharColor(CharColor.BLACK, CharColor.BLUE);
	final static CharColor 	CYAN 			= new CharColor(CharColor.BLACK, CharColor.CYAN);
	final static CharColor 	MAGENTA 		= new CharColor(CharColor.BLACK, CharColor.MAGENTA);
	final static CharColor 	WHITE 			= new CharColor(CharColor.BLACK, CharColor.WHITE);
}