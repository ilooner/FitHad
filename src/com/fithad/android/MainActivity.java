package com.fithad.android;

import java.util.Locale;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.ActiveAndroid;
import com.fithad.android.data.Event;
import com.fithad.android.data.Exercise;
import com.fithad.android.data.ExerciseEvent;
import com.fithad.android.data.ExerciseSet;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener 
{
	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;
	
	private DateFragment dateFragment;
	private ExerciseFragment exerciseFragment;
	private ExerciseDataFragment exerciseDataFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		///
		
        /*Configuration.Builder config = new Configuration.Builder(this);
        config.addModelClasses(Event.class,
        					   Exercise.class,
        					   ExerciseEvent.class,
        					   ExerciseSet.class);
        ActiveAndroid.initialize(config);*/
		
		///
		
		setContentView(R.layout.activity_main);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(),
				2);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setOffscreenPageLimit(2);
		

		dateFragment = new DateFragment(this);
		exerciseFragment = new ExerciseFragment(this);
		
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		for(int i = 0; 
			i < mSectionsPagerAdapter.getCount();
			i++) 
		{
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
	}
	
	public void removeExerciseDataTab()
	{
		final ActionBar actionBar = getSupportActionBar();
		
		if(actionBar.getTabCount() != 3)
		{
			throw new UnsupportedOperationException("This should not be called more than once.");
		}
		
		actionBar.removeTabAt(2);
	}
	
	public void addExerciseDataTab()
	{
		final ActionBar actionBar = getSupportActionBar();
		
		if(actionBar.getTabCount() != 2)
		{
			throw new UnsupportedOperationException("This should not be called more than once.");
		}
		
		mSectionsPagerAdapter.setCount(3);
		mSectionsPagerAdapter.notifyDataSetChanged();
		
		actionBar.addTab(actionBar.newTab().
						 setText(mSectionsPagerAdapter.getPageTitle(mSectionsPagerAdapter.getCount() - 1)).
						 setTabListener(this));
	}
	
	public boolean hasExerciseDataTab()
	{
		final ActionBar actionBar = getSupportActionBar();
		
		return actionBar.getTabCount() == 3; 
	}
	
	public void updatePager()
	{
		if(exerciseDataFragment != null)
		{
			exerciseDataFragment.setDate(dateFragment.getSelectedEvent().getDate());
			exerciseDataFragment.rebuild();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		
		if(id == R.id.action_settings) 
		{
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
							  FragmentTransaction fragmentTransaction)
	{
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
								FragmentTransaction fragmentTransaction)
	{
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
								FragmentTransaction fragmentTransaction)
	{
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter
	{
		private int count;
		
		public SectionsPagerAdapter(FragmentManager fm,
									int count)
		{
			super(fm);
			
			setCount(count);
		}

		@Override
		public Fragment getItem(int position)
		{
			switch(position)
			{
				case 0:
					return dateFragment;
				case 1:
					return exerciseFragment;
				case 2:
					exerciseDataFragment = new ExerciseDataFragment(dateFragment.getSelectedEvent().getDate());
					return exerciseDataFragment;
			}
			
			return null;
		}

		@Override
		public int getCount() {
			return count;
		}
		
		public void setCount(int count)
		{
			this.count = count;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			Locale l = Locale.getDefault();
			
			switch (position)
			{
				case 0:
					return getString(R.string.title_section1).toUpperCase(l);
				case 1:
					return getString(R.string.title_section2).toUpperCase(l);
				case 2:
					return getString(R.string.title_section3).toUpperCase(l);
			}
			
			return null;
		}
	}

}
