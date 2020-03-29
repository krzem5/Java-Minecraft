package com.krzem.minecraft;



import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.lang.Math;



public class Constants{
	public static final int DISPLAY_ID=0;
	public static final GraphicsDevice SCREEN=GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[DISPLAY_ID];
	public static final Rectangle WINDOW_SIZE=SCREEN.getDefaultConfiguration().getBounds();

	public static final String MODEL_DIR="./com/krzem/minecraft/assets/models/";

	public static final int IMAGE_SIZE=64;
	public static final String IMAGE_DIR="./com/krzem/minecraft/assets/textures/";

	public static final int CROSSHAIR_SIZE=40;

	public static final double CAMERA_MOVE_SPEED=0.5d;
	public static final double CAMERA_ROT_SPEED=0.075d;
	public static final double CAMERA_MIN_EASE_DIFF=0.05d;
	public static final double CAMERA_EASE_PROC=0.45d;
	public static final double CAMERA_CAM_NEAR=0.05d;
	public static final double CAMERA_CAM_FAR=1000d;

	public static final int CHUNK_SIZE=16;
	public static final int CHUNK_HEIGHT=256;

	public static final String BLOCK_CLASS_DIR="./com/krzem/minecraft/blocks/";
}