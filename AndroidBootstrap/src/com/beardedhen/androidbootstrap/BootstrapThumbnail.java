package com.beardedhen.androidbootstrap;

import java.util.HashMap;
import java.util.Map;




import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BootstrapThumbnail extends FrameLayout
{
	

	private static Map<String, ThumbnailTypes> bThumbnailTypeMap;
	private static Typeface font;
	private ViewGroup container;
	private LinearLayout placeholder;
	private TextView dimensionsLabel;
	
	static{
		
		bThumbnailTypeMap = new HashMap<String, ThumbnailTypes>();
		
		bThumbnailTypeMap.put("default", ThumbnailTypes.DEFAULT);
	}
	
	public BootstrapThumbnail(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
		initialise(attrs);
	}
	
	public BootstrapThumbnail(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		initialise(attrs);
	}
	
	public BootstrapThumbnail(Context context) 
	{
		super(context);
		initialise(null);
	}
	
	//set up the bootstrap types
	private enum ThumbnailTypes
	{		
		DEFAULT(R.drawable.bthumbnail_container_default,R.drawable.bthumbnail_placeholder_default);

		private int containerDrawable;
		private int placeholderDrawable;
		
		//private int textColour;
		
		ThumbnailTypes(int containerDrawable, int placeholderDrawable)
		{
			this.containerDrawable = containerDrawable;
			this.placeholderDrawable = placeholderDrawable;
			//this.textColour = textColour;
		}
	}
	
	private void initialise( AttributeSet attrs )
	{
		LayoutInflater inflator = (LayoutInflater)getContext().getSystemService(
			    Context.LAYOUT_INFLATER_SERVICE);

		//get font
		//readFont(getContext());

		TypedArray a = getContext().obtainStyledAttributes(attrs,
			    R.styleable.BootstrapThumbnail);
		
		//defaults
		ThumbnailTypes type = null;
		String thumbnailType = "default";
		String text = "";
		//boolean roundedCorners = false;
		float fontSize = 14.0f;
		float scale = getResources().getDisplayMetrics().density; //for padding
		int width = 75;
		int height = 75;
		int paddingA = (int) (10 *scale + 0.5f);
		int paddingB = (int) (15 *scale + 0.5f);
		

		//attribute values	
		if(a.getString(R.styleable.BootstrapThumbnail_bt_width) != null) {
			width = a.getInt(R.styleable.BootstrapThumbnail_bt_width, 0);
		}
		
		if(a.getString(R.styleable.BootstrapThumbnail_bt_width) != null) {
			height = a.getInt(R.styleable.BootstrapThumbnail_bt_height, 0);
		}
	
		a.recycle();
		
		text = width + "x" + height;
		View v = inflator.inflate(R.layout.bootstrap_thumbnail, null, false);
	
		//get layout items
		container = (ViewGroup) v.findViewById(R.id.container);
		placeholder = (LinearLayout) v.findViewById(R.id.placeholder);
		dimensionsLabel = (TextView) v.findViewById(R.id.dimensionsLabel);
		
		placeholder.setLayoutParams(new LinearLayout.LayoutParams(width,height));

		//set the background
		//setBootstrapType(bootstrapType);
		
		type = bThumbnailTypeMap.get(thumbnailType);

		
		//set up as default
		if (type == null)
		{
			type = ThumbnailTypes.DEFAULT;
		}
	
		//apply the background type
		container.setBackgroundResource(type.containerDrawable);
		placeholder.setBackgroundResource(type.placeholderDrawable);
		//dimensionsLabel.setTextColor(getResources().getColor(type.textColour));
		
		int padding = (int) (((Math.sqrt(width * height)) / 100) * 2);
		if(padding > 8)
			padding = 8;
		if(padding < 4)
			padding = 4;
		
		container.setPadding(8, 8, 8, 8);
		
		
		//set the font awesome icon typeface
		dimensionsLabel.setTypeface(font);
		
		//set up the font size
		dimensionsLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
		
		//dimensionsLabel.setPadding(paddingB, 0, paddingB, 0);
        
        //set the text 
        if(text.length() > 0){
        	dimensionsLabel.setText(text);
        	dimensionsLabel.setVisibility(View.VISIBLE);
        }

        this.setClickable(true);
        
        //this.setEnabled(enabled);

        //layout.setPadding(0, paddingB, 0, paddingB);
        
		addView(v);
	}
}
