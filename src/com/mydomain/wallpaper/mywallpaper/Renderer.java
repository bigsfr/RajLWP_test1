package com.mydomain.wallpaper.mywallpaper;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.materials.Material;
import rajawali.materials.textures.ATexture.TextureException;
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

		} catch (TextureException e) {
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
		// ArrayList<ATexture> l = material.getTextureList();
		//
		// if (l.size()>0) {
		// ATexture t = l.get(0);
		// material.removeTexture(t);
		// getTextureManager().removeTexture(t);
		//
		//
		// }
		// else {
		//
		// try {
		//
		// material.addTexture(new Texture("rajawaliTex", R.drawable.rajawali_tex));
		// p.setMaterial(material);
		// material.enableLighting(false);
		//
		// //getTextureManager().replaceTexture(new Texture("rajawaliTex", R.drawable.rajawali_tex));
		// } catch (TextureException e) {
		// e.printStackTrace();
		// }
		// }

		if (current_texture==0)
		{
			myTexture.setResourceId(R.drawable.rajawali_tex2);
			// or
			//myTexture.setBitmap(myNewBitmap);
			getTextureManager().replaceTexture(myTexture);	
			current_texture=1;
		}else if (current_texture==1){
			myTexture.setResourceId(R.drawable.rajawali_tex);
			// or
			//myTexture.setBitmap(myNewBitmap);
			getTextureManager().replaceTexture(myTexture);	
			current_texture=0;
		}

		Log.e("MY", "TextureManager texture count is now "+ getTextureManager().getNumTextures());
		Log.e("MY", " material texture count is now "+ material.getTextureList().size());
	}
}


