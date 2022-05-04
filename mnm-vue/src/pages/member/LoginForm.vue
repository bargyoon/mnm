<template>
  <div class="section" style="min-height:92vh">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-12 text-center align-self-center py-5">
          <div class="section pb-5 pt-5 pt-sm-2 text-center">
            <div class="card-3d-wrap mx-auto">
              <div class="card-3d-wrapper">
                <div class="card-front">
                  <div class="center-wrap">
                    <b-form-group class="section text-center">
                      <h4 class="mb-4 pb-3">Log In</h4>
                      <div class="form-group">
                        <b-form-input type="text" class="form-style" v-model="userId"
                               placeholder="아이디" id="userId" autocomplete="off">
                        </b-form-input>
                        <b-icon icon="person-fill" class="input-icon"></b-icon>
                      </div>
                      <div class="form-group mt-2">
                        <b-form-input type="password" v-model="password" class="form-style"
                               placeholder="비밀번호" id="password" autocomplete="off">
                        </b-form-input>
                        <b-icon icon="file-lock" class="input-icon"></b-icon>
                      </div>
                      <span style="color: moccasin">아이디 또는 비밀번호가 틀렸습니다.</span>
                      <p class="mb-0 mt-4 text-center">
                        <a href="/member/forget-password" class="link">비밀번호를
                          잊으셨습니까?</a>
                      </p>
                      <button class="btn original mt-2 mb-2" @click="login">로그인</button>
                      <div class="border-bottom "></div>
                      <a href="javascript:loginFormWithKakao()"
                         class="btn kakao mt-2">카카오로 로그인</a>

                      <div class="col-md-12">

                        <div class="signup-section">
                          Not a member yet? <a href="/member/joinRule" style="color: orange"> Sign Up</a>.
                        </div>
                      </div>
                    </b-form-group>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginForm",
  data() {
    return{
      userId: '',
      password: '',
    }
  },
  methods: {
    login() {
      if(this.userId.trim() === '' || this.password.trim() === '') {
        return;
      }
      axios.post('/member/login',  {userId: this.userId, password: this.password})
          .then(response => {
            console.log(response)

          })
          .catch(error => {
            console.log(error);
          })

    }
  }
}
</script>

<style scoped>
@import '../../assets/css/member/style.css';

</style>