package qulix.com.puremvponloaders.mvp.activity;

import qulix.com.puremvponloaders.mvp.MvpPresenter;
import qulix.com.puremvponloaders.mvp.MvpView;

/**
 * Base Mvp presenter class for using in activities.
 *
 * @param <V> type of mvp activity view
 * @author Anrey Yablonsky
 */
public abstract class MvpActivityPresenter<V extends MvpView> extends MvpPresenter<V> {


}
