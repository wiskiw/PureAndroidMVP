package qulix.com.puremvponloaders.mvp.fragment;

import qulix.com.puremvponloaders.mvp.MvpPresenter;
import qulix.com.puremvponloaders.mvp.MvpView;

/**
 * Base Mvp presenter class for using in fragments.
 *
 * @param <V> type of mvp fragment view
 * @author Anrey Yablonsky
 */
public abstract class MvpFragmentPresenter<V extends MvpView> extends MvpPresenter<V> {


}
