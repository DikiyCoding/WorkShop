package com.dikiy.workshop.ui.design.examples.first.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dikiy.workshop.R
import com.dikiy.workshop.ui.design.examples.first.ui.adapters.PursesAdapter
import kotlinx.android.synthetic.main.fragment_purses_main.*
import com.dikiy.workshop.ui.design.examples.first.model.Header
import com.dikiy.workshop.ui.design.examples.first.model.Purse
import com.dikiy.workshop.ui.design.examples.first.utils.Constants
import com.dikiy.workshop.ui.design.examples.first.utils.VerticalLastItemMarginDecoration

class MainFragment : Fragment() {

    private lateinit var mHeader: Header
    private lateinit var mPurses: MutableList<Purse>
    private lateinit var mPursesAdapter: PursesAdapter
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_purses_main, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initList()
        initAdapter()
        assignList()
    }
    
    private fun initList() {
        mHeader = Header("3 290,75 \u20BD", true,
                R.drawable.ic_purses_wallet_grey,
                "Завтра день зарплаты \uD83C\uDF89",
                "+ 30 000 \u20BD")
        mPurses = mutableListOf(
                Purse("Вчера",
                        R.drawable.ic_purses_arrow_down,
                        ContextCompat.getColor(requireContext(), R.color.colorPursesEarnings),
                        "Зарплата",
                        "+ 22 578,81 \u20BD"),
                Purse(null,
                        R.drawable.ic_purses_arrow_up,
                        ContextCompat.getColor(requireContext(), R.color.colorPursesMain),
                        "Вывод денег",
                        "- 1 050 \u20BD"),
                Purse("29 сентября, пн",
                        R.drawable.ic_purses_arrow_up,
                        ContextCompat.getColor(requireContext(), R.color.colorPursesMain),
                        "Вывод денег в две строки",
                        "- 16 085,75 \u20BD"),
                Purse(null,
                        R.drawable.ic_purses_arrow_up,
                        ContextCompat.getColor(requireContext(), R.color.colorPursesMain),
                        "Вывод денег",
                        "- 1 750 \u20BD"),
                Purse(null,
                        R.drawable.ic_purses_arrow_down,
                        ContextCompat.getColor(requireContext(), R.color.colorPursesEarnings),
                        "Бонусы",
                        "+ 54,10 \u20BD"),
                Purse("25 сентября, пн",
                        R.drawable.ic_purses_wallet_green,
                        ContextCompat.getColor(requireContext(), R.color.colorPursesEarnings),
                        "Бонусы",
                        "+ 20 000 \u20BD"))
    }

    private fun initAdapter() {
        mPursesAdapter = PursesAdapter(mHeader, mPurses,
                callbackHeader = { id -> onItemHeaderClick(id)},
                callbackPurses = { position -> onItemPursesClick(position)})
    }

    private fun assignList() {
        list_purses.adapter = mPursesAdapter
        list_purses.addItemDecoration(
                VerticalLastItemMarginDecoration(Constants.MARGINS.MARGIN24.value, mPurses.size)
        )
    }

    private fun onItemHeaderClick(id: Int) {
        var btnName = "Some"
        when(id) {
            R.id.ib_info -> btnName = "Info"
            R.id.tv_details -> btnName = "Details"
            R.id.btn_money_pick_up -> btnName = "Details"
        }
        Log.d(Constants.LOGS.EVENTS.tag, "$btnName button is pressed")
    }

    private fun onItemPursesClick(position: Int) {
        Log.d(Constants.LOGS.EVENTS.tag, "Position in \"Purses\" list: $position")
    }
}
