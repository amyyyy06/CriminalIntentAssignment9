package android.bignerdranch.assignment9;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.FragmentActivity;
import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private List<Crime> mCrimes;
    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.assignment9.crime_id";

    @Override
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager2)findViewById(R.id.crime_view_pager2);
        mCrimes = CrimeLab.get(this).getCrimes();
        //FragmentActivity fragmentActivity = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public Fragment createFragment(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getID());
            }

            @Override
            public int getItemCount() {
                return mCrimes.size();
            }
        });

        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        for(int i = 0; i < mCrimes.size(); i++) {
            if(mCrimes.get(i).getID().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(
            Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext,
                CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
}
