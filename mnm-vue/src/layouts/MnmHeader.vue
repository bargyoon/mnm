<template>
  <div class="flex-shrink-0 container">
    <div class="site-navbar-wrap">
      <div class="site-navbar site-navbar-target js-sticky-header">
          <div class="row align-items-center" style="width: 100%;">
            <div class="col-4" style="display: flex; justify-content: center">
              <a class="navbar-brand d-none d-lg-block" href="/index">MENTOR & MENTEE</a>
              <a class="navbar-brand d-lg-none ml-md-0 mr-auto" href="/index">M & M</a>
            </div>
            <div class="col-8"
                 style="display: flex; justify-content: flex-end;">
              <b-nav class="site-navigation text-right" role="navigation">
                <b-container>
                  <div class="d-inline-block d-lg-none ml-md-0 mr-auto py-3">
                    <a href="#" class="site-menu-toggle js-menu-toggle text-white"><span
                        class="icon-menu h3" style="color:white"></span></a>
                  </div>

                  <div class="site-menu main-menu js-clone-nav d-none d-lg-block">
                    <!--                    <b-nav-item><a href="/member/login" class="nav-link">로그인</a></b-nav-item>-->
                    <!--                    <b-nav-item><a href="/member/join" class="nav-link">회원가입</a></b-nav-item>-->


                    <b-nav-item class="nav-link">멘토신청</b-nav-item>
                    <b-nav-item class="nav-link">멘토링 관리</b-nav-item>
                    <b-nav-item class="nav-link" href="/board/mentor">멘토게시판
                      <!--                          <a href="/meboard/mentee" class="nav-link">멘티게시판</a>-->
                    </b-nav-item>

                    <b-nav-item class="nav-link">일정관리</b-nav-item>
                    <b-nav-item-dropdown text="안녕하세요" class="nav-link">
                      <b-dropdown-item class="mypage"><a href="/mentoring/manage-page" style="color:black">마이페이지</a>
                      </b-dropdown-item>
                      <b-dropdown-item><a href="/mentoring/manage-page" style="color:black">로그아웃</a></b-dropdown-item>
                    </b-nav-item-dropdown>


                  </div>
                </b-container>
              </b-nav>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "MnmHeader",
}
</script>

<style scoped>

</style>