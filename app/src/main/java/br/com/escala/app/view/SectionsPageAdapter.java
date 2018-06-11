package br.com.escala.app.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.escala.app.view.fragment.CategoriasFragment;
import br.com.escala.app.view.fragment.LancamentosFragment;
import br.com.escala.app.view.fragment.MinhaBancaFragment;
import br.com.escala.app.view.fragment.TitulosFragment;

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
