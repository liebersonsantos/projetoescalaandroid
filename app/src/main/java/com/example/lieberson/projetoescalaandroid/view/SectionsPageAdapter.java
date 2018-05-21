package com.example.lieberson.projetoescalaandroid.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lieberson.projetoescalaandroid.view.fragment.CategoriasFragment;
import com.example.lieberson.projetoescalaandroid.view.fragment.LancamentosFragment;
import com.example.lieberson.projetoescalaandroid.view.fragment.MinhaBancaFragment;
import com.example.lieberson.projetoescalaandroid.view.fragment.TitulosFragment;

class SectionsPageAdapter extends FragmentStatePagerAdapter {

    SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        Fragment fragment;

        switch (position) {

            case 0:
                fragment = new LancamentosFragment();
                break;
            case 1:
                fragment = new MinhaBancaFragment();
                break;
            case 2:
                fragment = new CategoriasFragment();
                break;
            case 3:
                fragment = new TitulosFragment();
                break;
            default:
                fragment = new LancamentosFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
