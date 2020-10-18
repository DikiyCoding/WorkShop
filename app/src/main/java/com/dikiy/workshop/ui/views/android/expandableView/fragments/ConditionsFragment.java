package com.dikiy.workshop.ui.views.android.expandableView.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.views.android.expandableView.adapters.StepsAdapter;
import com.dikiy.workshop.ui.views.android.expandableView.model.Step;
import com.github.florent37.expansionpanel.ExpansionLayout;

import java.util.ArrayList;
import java.util.List;

import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.Markwon;
import io.noties.markwon.core.MarkwonTheme;

public class ConditionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conditions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        initListSteps(view);
        initMarkdownInfo(view);
        addListeners(view);
    }

    private void initListSteps(View view) {
        RecyclerView rvSteps = view.findViewById(R.id.rv_steps);
        rvSteps.setAdapter(new StepsAdapter(getSteps(view)));
        rvSteps.setLayoutManager(new LinearLayoutManager(requireContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    private void initMarkdownInfo(View view) {

        String strMarkdown = "##### **Tmall**\nДля покупок на Tmall c кэшбэком теперь нужно выбирать [эту страницу.](https://epn.bz/ru/cashback/shops/tmall/)\nПредыдущая схема с получением кэшбэка на Tmall в рамках AliExpress больше недоступна. Срок обработки ранее совершенных заказов остался прежним - до 120 дней. \n\n**Важно!**\n  \n*  Кэшбэк начисляется на стоимость товаров без учета стоимости доставки и составляет не более 45$ за один заказ. \n*  На сумму гарантии кэшбэк начисляется по решению магазина на AliExpress\n*  Кэшбэк доступен только за товары от китайских продавцов. За товары продавцов из всех остальных стран кэшбэк не начисляется. \n*  Товар должен быть добавлен в корзину после активации кэшбэка. В противном случае, кэшбэк может быть не засчитан. \n*  За Hotsale товары, в том числе товары из раздела \"Горящее предложение\", кэшбэк может быть рассчитан по стандартной ставке. Подобное происходит, если магазин на AliExpresss передает нам информацию об изменениях с запозданием и не начисляет повышенный процент. Мы вас оповестим, как только AliExpress устранит данную проблему.\n\nТакже кэшбэк НЕ начисляется за:\n*  транспортные расходы \n*  виртуальные товары  и  услуги\n*  подарочные сертификаты\n*  оплату мобильной связи \n*  товары по акции \"Скидка на двоих\" из раздела Lowcoster в мобильном приложении AliExpress\n\n\n**Просим также обратить внимание!**\n*  При переходе на AliExpress по сторонним ссылкам, например вида http://s.click.logo_ali.com кэшбэк может быть не засчитан.\nРекомендуем вам совершать покупки после непосредственного перехода через наш сервис ePN Cashback. \n*  Промокоды ePN Cashback не действуют на [товары с повышенным кэшбэком](https://epn.bz/ru/cashback/actions), все Hotsale товары и подарочные сертификаты AliExpress.\n* По некоторым заказам, информацию о подтверждении получения товара магазин передает нам с задержкой. Это заказы, по которым AliExpress проводит дополнительную проверку на «фрод». Время подтверждения кэшбэка в таком случае может занимать до 45 дней.\n*  При выявлении «фрода», аккаунт в системе ePN Cashback  может быть заблокирован, а кэшбэк списан с баланса. \nНапример, создание множественных аккаунтов AliExpress для получения той или иной выгоды, которая предназначена для новых пользователей, или для обхода ограничений по количеству купонов/скидок и т.д. которые распространяются на один аккаунт AliExpress, считаются нарушением правил AliExpress и кэшбэк за подобные заказы  может быть отменен. \n*  В случае открытия спора по заказу, сумма кэшбэка по нему будет удержана с вашего баланса. \n*  Кэшбэк может не зачитаться при использовании различных медиа-промокодов, полученных вне сервиса ePN Cashback.   \n* [Купоны AliExpress](https://epn.bz/cashback/coupons) - это подборка товаров со скидкой от продавца. Цена товара указана с учетом купона. При открытии ссылки на товар Вы увидите его финальную стоимость и скидку, которую установил продавец.\n* При использовании купонов на покупку Hotsale товаров (в том числе товаров из раздела \"Горящее предложение\"), кэшбэк может быть отклонен по решению AliExpress. \n\n\n\n\n\n\n\n\n\n\n";

        Markwon markwon = Markwon.builder(requireContext())
                .usePlugin(new AbstractMarkwonPlugin() {
                    @Override
                    public void configureTheme(@NonNull MarkwonTheme.Builder builder) {
                        builder.linkColor(getResources().getColor(R.color.colorBlueMain))
                                .listItemColor(Color.RED)
                                .headingTextSizeMultipliers(new float[]{1, 1, 1, 1, 1, 1});
//                                .bulletWidth(10);
                    }
                })
                .build();
        markwon.setMarkdown(view.findViewById(R.id.tv_markdown), strMarkdown);
    }

    private List<Step> getSteps(View view) {
        List listSteps = new ArrayList<>();
        String[] headers = view.getResources().getStringArray(R.array.exp_layout_titles);
        String[] descriptions = view.getResources().getStringArray(R.array.exp_layout_descriptions);
        int[] imageRes = getImageRes();
        for (int i = 0; i < 3; i++)
            listSteps.add(new Step(headers[i], descriptions[i], imageRes[i]));
        return listSteps;
    }

    private int[] getImageRes() {
        return new int[] {
                R.drawable.ic_off_ads,
                R.drawable.ic_use_android,
                R.drawable.ic_buy_on_site
        };
    }

    private void addListeners(View view) {
        final ExpansionLayout expansionLayout = view.findViewById(R.id.exp_layout);
        ((ToggleButton) view.findViewById(R.id.toggle_btn_action_exp))
                .setOnCheckedChangeListener((compoundButton, b) ->
                        expansionLayout.toggle(true));
    }
}
