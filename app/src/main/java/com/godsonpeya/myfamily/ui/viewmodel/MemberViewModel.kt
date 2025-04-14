package com.godsonpeya.myfamily.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godsonpeya.myfamily.data.remote.model.MembersItem
import com.godsonpeya.myfamily.data.remote.model.MembersItemRq
import com.godsonpeya.myfamily.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MemberViewModel @Inject constructor(private val memberRepository: MemberRepository) :
    ViewModel() {

    private var _members = MutableStateFlow(emptyList<MembersItem>())

    val members: StateFlow<List<MembersItem>> = _members

    private var _member = MutableStateFlow<MembersItem?>(null)
    val member: StateFlow<MembersItem?> = _member

    fun getMembers() {
        viewModelScope.launch {
            _members.value = memberRepository.getMembers()
        }
    }

    fun getMember(id: String) {
        viewModelScope.launch {
            _member.value =  memberRepository.getMember(id)
        }
    }

    fun addMember(member: MembersItemRq) {
        viewModelScope.launch {
            memberRepository.addMember(member)
        }
    }

    fun updateMember(id: String, member: MembersItemRq) {
        viewModelScope.launch {
            memberRepository.updateMember(id, member)
        }
    }

    fun deleteMember(id: String) {
        viewModelScope.launch {
            memberRepository.deleteMember(id)
        }
    }



}