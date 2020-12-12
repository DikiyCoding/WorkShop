package com.dikiy.workshop.ui.design.examples.first.ui.activities

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.dikiy.workshop.R
import com.dikiy.workshop.ui.design.examples.first.ui.adapters.CategoriesAdapter
import kotlinx.android.synthetic.main.content_activity_permits.*
import com.dikiy.workshop.ui.design.examples.first.model.Category
import com.dikiy.workshop.ui.design.examples.first.model.Permit
import com.dikiy.workshop.ui.design.examples.first.ui.adapters.PermitsAdapter
import com.dikiy.workshop.ui.design.examples.first.utils.Constants
import com.dikiy.workshop.ui.design.examples.first.utils.StyleUtils
import com.dikiy.workshop.ui.design.examples.first.utils.VerticalLastItemMarginDecoration

class PermitsActivity : AppCompatActivity() {

    private lateinit var mPermits: MutableList<Permit>
    private lateinit var mCategories: MutableList<Category>
    private lateinit var mPermitsAdapter: PermitsAdapter
    private lateinit var mCategoriesAdapter: CategoriesAdapter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permits)
        init()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun init() {
        initToolbar()
        createLists()
        fillLists()
        createAdapters()
        assignPermitsList()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initToolbar() {
            StyleUtils.setStatusBarColor(this, R.color.colorPermitsBackground)
    }

    private fun createLists() {
        mPermits = ArrayList()
        mCategories = ArrayList()
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun fillLists() {
        for (i in 1..2) {
            getDrawable(R.drawable.img_permits_subscriptions)
                    ?.let { Category(R.drawable.ic_permits_subscriptions, "Подписки", it) }
                    ?.let { mCategories.add(it) }
            getDrawable(R.drawable.img_permits_flights)
                    ?.let { Category(R.drawable.ic_permits_flights, "Авиабилеты", it) }
                    ?.let { mCategories.add(it) }
            getDrawable(R.drawable.img_permits_tours)
                    ?.let { Category(R.drawable.ic_permits_tours, "Туры", it) }
                    ?.let { mCategories.add(it) }
            getDrawable(R.drawable.img_permits_hotels)
                    ?.let { Category(R.drawable.ic_permits_hotels, "Отели", it) }
                    ?.let { mCategories.add(it) }
            mPermits.add(Permit(R.drawable.ic_permits_ship,
                    Html.fromHtml("Круиз с безвиззовой" +
                            "<br> <b>Англией</b> – 259€",
                            HtmlCompat.FROM_HTML_MODE_LEGACY),
                    "7 дней в апреле 2019 года - vandrouki"))
            mPermits.add(Permit(R.drawable.ic_permits_airplane,
                    Html.fromHtml("Из Москвы <b>в Пекин</b> " +
                            "\uD83C\uDDE8\uD83C\uDDF3<br> за 16 800 р. RT (июнь)",
                            HtmlCompat.FROM_HTML_MODE_LEGACY),
                    "MIAT, по пути можно посмотреть столицу Монголии"))
            mPermits.add(Permit(R.drawable.ic_permits_travel,
                    Html.fromHtml("Тур из Москвы <b>в Тунис</b> " +
                            "\uD83C\uDDF9\uD83C\uDDF3 –<br>15 400 р./чел.",
                            HtmlCompat.FROM_HTML_MODE_LEGACY),
                    "Вылет 4 мая на 7 ночей"))
            mPermits.add(Permit(R.drawable.ic_permits_travel,
                    Html.fromHtml("Тур из Москвы <b>на Мальорку</b> " +
                            "\uD83C\uDDEA\uD83C\uDDF8 –<br>20 500 р./чел.",
                            HtmlCompat.FROM_HTML_MODE_LEGACY),
                    "Вылет 29 мая на 3 ночи"))
            mPermits.add(Permit(R.drawable.ic_permits_flame,
                    Html.fromHtml("Тур из Москвы <b>в Тунис</b> " +
                            "\uD83C\uDDF9\uD83C\uDDF3 –<br>15 400 р./чел.",
                            HtmlCompat.FROM_HTML_MODE_LEGACY),
                    "Вылет 4 мая на 7 ночей"))
        }
    }

    private fun createAdapters() {
        mCategoriesAdapter = CategoriesAdapter(mCategories) { position -> onItemCategoryClick(position) }
        mPermitsAdapter = PermitsAdapter(mCategoriesAdapter, mPermits) { position -> onItemPermitsClick(position) }
    }

    private fun assignPermitsList() {
        list_permits.adapter = mPermitsAdapter
        list_permits.addItemDecoration(
                VerticalLastItemMarginDecoration(Constants.MARGINS.MARGIN20.value, mPermits.size)
        )
    }

    private fun onItemCategoryClick(position: Int) {
        Log.d(Constants.LOGS.EVENTS.tag, "Position in \"Categories\" list: $position")
    }

    private fun onItemPermitsClick(position: Int) {
        Log.d(Constants.LOGS.EVENTS.tag, "Position in \"Permits\" list: $position")
    }
}
