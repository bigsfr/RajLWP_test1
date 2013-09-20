package com.mydomain.wallpaper.mywallpaper;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.materials.Material;
import rajawali.materials.textures.Texture;
import rajawali.primitives.Plane;
import rajawali.renderer.RajawaliRenderer;
import rajawali.util.RajLog;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;


public class Renderer extends RajawaliRenderer {

	private Plane p = null;
	private Material material = null;
	int current_texture = 0;
	Texture myTexture = null;

	public Renderer(Context context) {
		super(context);
	}

	public void initScene()
	{
		Log.e("MY", "initScene");
		RajLog.enableDebug(false);

		getCurrentCamera().setPosition(0, 0, -7);
		getCurrentCamera().setLookAt(0, 0, 0);

		try {
			p = new Plane(5,5,1,1);
			material = new Material();
			material.enableLighting(false);

			myTexture = new Texture("rajawaliTex", R.drawable.rajawali_tex);
			material.addTexture(myTexture);
			p.setMaterial(material);
			getCurrentScene().addChild(p);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		Log.e("MY", "onSurfaceCreated");
		super.onSurfaceCreated(gl, config);	
	}

	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		Log.e("MY", "touched");


		if (current_texture==0)
		{
			
			myTexture.setResourceId(R.drawable.rajawali_tex2);
			getTextureManager().replaceTexture(myTexture);	
			current_texture=1;
			
		}else if (current_texture==1){
			
			myTexture.setResourceId(R.drawable.rajawali_tex);
			getTextureManager().replaceTexture(myTexture);	
			current_texture=0;
			
		}

		Log.e("MY", "TextureManager texture count is now "+ getTextureManager().getNumTextures());
		Log.e("MY", " material texture count is now "+ material.getTextureList().size());
	}
}