package com.professionalandroid.apps.androider.navigation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.professionalandroid.apps.androider.*
import com.professionalandroid.apps.androider.newsfeed.*
import com.professionalandroid.apps.androider.newsfeed.commonsearch.AllSearchView
import com.professionalandroid.apps.androider.newsfeed.item.ItemFrag
import com.professionalandroid.apps.androider.newsfeed.place.PlaceFrag
import com.professionalandroid.apps.androider.newsfeed.user.UserFrag
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_newsfeed.view.*

class NewsFeedFragment() : Fragment(){
    companion object{
        var flag:Boolean=true
        lateinit var thisFragment: NewsFeedFragment
    }
    var pageList= arrayListOf<Fragment>()
    var nameList=arrayOf("장소","아이템","사용자")
    var index:Int=0
    var preIndex:Int=-1
    lateinit var searchFragment: AllSearchView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_newsfeed, container, false)
        thisFragment=this
        view.tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF4500")) //탭바 밑줄 색상 변경.
        makeFragment()
        searchFragment=
            AllSearchView(
                view
            )
        tabListener(view)
        setSearchButton(view)
        makeViewPage(view)//View Pager 만들기.
        return view
    }
    private fun makeFragment(){
        var page1 = PlaceFrag()
        var page2 = ItemFrag()
        var page3 = UserFrag()
        pageList.add(page1)
        pageList.add(page2)
        pageList.add(page3)
    }
    private fun makeViewPage(view:View) {
//        val adapter = PageAdapter(childFragmentManager)
//        adapter.addItems(pageList[0])//어뎁터에 원하는 fragment 삽입.
//        adapter.addItems(pageList[1])
//        adapter.addItems(pageList[2])
//        view.viewPager.adapter = adapter//view pager 에 adapter 장착.
//        view.tabLayout.setupWithViewPager(view.viewPager)//tabLayout 과 view pager 연동.
        val adapter= PageAdapter(
            requireActivity()
        )
        adapter.setFragment(pageList)
        view.viewPager.adapter=adapter
        TabLayoutMediator(view.tabLayout,view.viewPager){ tab,position ->
            tab.text=nameList[position]
        }.attach()
    }
    private fun tabListener(view:View){
        view.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    preIndex=index
                    index=p0.position
                }
                if(!flag){
//                    if(index==0) {
//                        requireActivity().supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frame, searchFragment).commit()
//                        Log.d("test222222","ssssss")
//                    }
//                    else if(index==1) {
//                        requireActivity().supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frame, searchFragment).commit()
//                        Log.d("test3333","ssssss")
//                    }
//                    else if(index==2)
//                        requireActivity().supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frame,searchFragment).commit()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame,searchFragment).hide(pageList[index]).commit()
                    AllSearchView.index=index
                    searchFragment.setHint(searchFragment.searchView)
                }
            }
        })
    }
    private fun setSearchButton(view: View){
        view.search_button.setOnClickListener {
            makeSearchView(view)
            flag=false
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
                .add(R.id.main_frame,searchFragment).hide(pageList[index]).commit()
            AllSearchView.index=index
            requireActivity().navigation_main_bottom.visibility=View.GONE//네비게이션 바를 잠시 없애는 것.(공간 차지 o)
            view.search_button.visibility=View.GONE
            //View.INVISIBLE - View 를 감춤(공간 차지 x) View.VISIBLE - View 를 보여줌(공간 차지 o)
        }
    }
    private fun makeSearchView(view:View){
        searchFragment= AllSearchView(view)
    }
//    override fun onBackPressed() {
//
//    }
}