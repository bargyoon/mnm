<template>
  <div class="container" style="padding-top: 100px">

    <!-- Registeration Form -->
    <div class="col-md-7 col-lg-6 m-auto">
      <b-form-group>
        <div class="row" style="padding: 22px;">
          <!-- Name -->
          <div class="input-group col-lg-12 mb-4">
            <div class="input-group-prepend">
							<span class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fa fa-user text-muted"></i>
							</span>
            </div>
            <b-form-input id="userName" v-model="user.userName" class="form-control bg-white border-left-0 border-md"
                          :state="nameValidation" placeholder="이름" :required="true"/>
          </div>
          <!-- UserId -->
          <div class="input-group col-lg-12 mb-4">
            <div class="input-group-prepend">
							<span class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fa fa-user text-muted"></i>
							</span>
            </div>
            <b-form-input id="userId" v-model="user.userId" class="form-control bg-white border-left-0 border-md"
                          :state="checkId" placeholder="아이디" required type="text"/>
            <button class="btn btn-outline-secondary checkIdBtn" @click="clickCheckId"
                    style="font-weight: bold;">중복검사
            </button>
          </div>

          <!-- Password -->
          <div class="input-group col-lg-6 mb-4">
            <div class="input-group-prepend">
							<span class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fa fa-lock text-muted"></i>
							</span>
            </div>
            <b-form-input id="password" v-model="user.password" class="form-control bg-white border-left-0 border-md"
                          :state="pwValidation" placeholder="비밀번호" required type="password"/>
          </div>
          <!-- Password Confirmation -->
          <div class="input-group col-lg-6 mb-4">
            <div class="input-group-prepend">
							<span
                  class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fa fa-lock text-muted"></i>
							</span>
            </div>
            <b-form-input id="rePassword" v-model="rePassword" class="form-control bg-white border-left-0 border-md"
                          :state="rePwValidation" name="rePassword" placeholder="비밀번호 확인" required type="password"/>
          </div>

          <!-- Email Address -->
          <div class="input-group col-lg-12 mb-4">
            <div class="input-group-prepend">
							<span class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fa fa-envelope text-muted"></i>
							</span>
            </div>
            <b-form-input id="email" v-model="user.email" class="form-control bg-white border-left-0 border-md"
                          :state="mailValidation" placeholder="이메일" required type="email"/>
          </div>

          <!-- Gender -->
          <div class="input-group col-lg-12 mb-4">
            <div class="input-group-prepend">
							<span class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fas fa-venus-mars text-muted"></i>
							</span>
            </div>
            <div id="gender" class="form-control bg-white border-left-0 border-md text-muted" name="gender"
                 placeholder="성별" style="max-width: 80px; display: flex; align-content: center; flex-wrap: wrap; justify-content: flex-start;">성별</div>
            <div class="form-control bg-white border-left-0 border-md h-70 font-weight-bold"
                style="display: flex; justify-content: space-around; align-content: center; flex-wrap: wrap;">
              <b-form-radio id="gender-m" v-model="user.gender" checked name="gender-radio" value="m">남</b-form-radio>
              <b-form-radio id="gender-f" v-model="user.gender" name="gender-radio" value="f">여</b-form-radio>
            </div>
          </div>
          <!-- Address -->
          <div class="input-group col-lg-12 mb-4">
            <div class="input-group-prepend">
							<span class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fas fa-home text-muted"></i>
							</span>
            </div>
            <b-form-input id="address" v-model="user.address" class="form-control bg-white border-left-0 border-md"
                          :state="addressValidation" placeholder="주소" required />
          </div>
          <!-- Phone Number -->
          <div class="input-group col-lg-12 mb-4">
            <div class="input-group-prepend">
							<span class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fa fa-phone-square text-muted"></i>
							</span>
            </div>
            <b-form-input id="phone" v-model="user.phone" class="form-control bg-white border-md border-left-0 pl-3"
                          placeholder="전화번호(-빼고 입력)" :state="phoneValidation" required type="tel"/>
            <b-form-invalid-feedback :state="phoneValidation">전화번호는 9-11자리의 숫자입니다.</b-form-invalid-feedback>

          </div>

          <template v-if="userType === 'mentor'">
            <!-- Divide Line -->
            <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
              <div class="border-bottom " style="width: 40%;"></div>
              <span class="px-2 small text-muted font-weight-bold text-muted"
                    style="width: 20%; text-align: center">대학교 정보</span>
              <div class="border-bottom " style="width: 40%;"></div>
            </div>

            <!-- 대학명 -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-school text-muted"></i>
                </span>
              </div>
              <b-form-input id="universityName" class="form-control bg-white border-left-0 border-md"
                            v-model="mentor.universityName" placeholder="재학중인 대학명" required />
            </div>

            <!-- 대학 종류 -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-school text-muted"></i>
                </span>
              </div>
              <b-form-select id="universityType" class="form-control custom-select bg-white border-left-0 border-md"
                      v-model="mentor.universityType" required :options="universityOptions">

              </b-form-select>
            </div>

            <!-- 현재학년 -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-user-graduate text-muted"></i>
                </span>
              </div>
              <b-form-select id="grade" class="form-control custom-select bg-white border-left-0 border-md"
                             v-model="mentor.grade" required :options="mentorGradeOptions">
              </b-form-select>
            </div>
            <!-- 전공 계열 -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-university text-muted"></i>
                </span>
              </div>
              <b-form-select id="major" class="form-control custom-select bg-white border-left-0 border-md"
                             v-model="mentor.major" required :options="majorOptions">
              </b-form-select>
            </div>

            <!-- Divide Line -->
            <div
                class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
              <div class="border-bottom " style="width: 35%;"></div>
              <span class="px-2 small text-muted font-weight-bold text-muted"
                    style="width: 30%; text-align: center">멘토링 요청사항</span>
              <div class="border-bottom " style="width: 35%;"></div>
            </div>
            <!-- Want day -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-calendar-day text-muted"></i>
                </span>
              </div>
              <b-form-select id="wantDay" class="form-control custom-select bg-white border-left-0 border-md"
                             v-model="mentor.wantDay" required :options="dayOptions">
              </b-form-select>
            </div>

            <!-- Want Time -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
							<span
                  class="input-group-text bg-white px-4 border-md border-right-0">
								<i class="fas fa-clock text-muted"></i>
							</span>
              </div>
              <b-form-select id="wantTime" class="form-control custom-select bg-white border-left-0 border-md"
                             v-model="mentor.wantTime" required :options="timeOptions">
              </b-form-select>
            </div>

            <!-- requirement -->
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-atom text-muted"></i>
                </span>
              </div>
              <b-form-select id="requirement" class="form-control custom-select bg-white border-left-0 border-md"
                      v-model="mentor.requirement" required :options="requireOptions">
              </b-form-select>
            </div>
            <!-- 이력사항 -->
            <div>
              <div id="historyArea" class="form-group focused">
                <div style="display: flex">
                  <label>이력사항</label>
                  <div style="display: flex; position: absolute; right: 40px">
                    <small class="form-text text-muted">이력사항을 추가하시려면 +버튼을
                      눌러주십시오</small> <a id="addHistoryBtn" class="btn btn-success btn-sm">+</a>
                  </div>
                </div>
                <b-form-input id="history0" class="form-control bg-white border-md" v-model="mentor.history"
                       placeholder="이력사항을 적어주세요(선택)" type="text" />
              </div>
            </div>


            <!-- Divide Line -->
            <div
                class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
              <div class="border-bottom " style="width: 40%;"></div>
              <span class="px-2 small text-muted font-weight-bold text-muted"
                    style="width: 20%; text-align: center">계좌 정보</span>
              <div class="border-bottom " style="width: 40%;"></div>
            </div>
            <!-- 계좌번호 -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-money-check text-muted"></i>
                </span>
              </div>
              <b-form-input id="accountNum" class="form-control bg-white border-left-0 border-md" v-model="mentor.accountNum"
                     placeholder="계좌번호 (- 빼고 입력하세요)" required type="text" />
            </div>
            <!-- 은행명 -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-piggy-bank text-muted"></i>
                </span>
              </div>
              <b-form-input id="bankName" class="form-control bg-white border-left-0 border-md" v-model="mentor.bankName"
                            placeholder="은행명" required type="text"/>
            </div>

          </template>
          <template v-else-if="userType === 'mentee'">
            <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
              <div class="border-bottom " style="width: 40%;"></div>
              <span class="px-2 small text-muted font-weight-bold text-muted"
                    style="width: 20%; text-align: center">학교 정보</span>
              <div class="border-bottom " style="width: 40%;"></div>
            </div>

            <!-- College name -->
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-school text-muted"></i>
                </span>
              </div>
              <b-form-input id="schoolName" v-model="mentee.schoolName"
                            class="form-control bg-white border-left-0 border-md" placeholder="재학중인 학교명"
                            required type="text"/>
            </div>

            <!-- 현재 계열 -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-user-graduate text-muted"></i>
                </span>
              </div>
              <b-form-select id="major" v-model="mentee.major" :options="menteeMajors"
                             class="form-control custom-select bg-white border-left-0 border-md text-muted"
                             required>
              </b-form-select>
            </div>

            <!-- 현재학년 -->
            <div class="input-group col-lg-6 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-user-graduate text-muted"></i>
                </span>
              </div>
              <b-form-select id="grade" v-model="mentee.grade" :options="gradeOptions"
                             class="form-control custom-select bg-white border-left-0 border-md text-muted"
                             required>
              </b-form-select>
            </div>
            <!-- Want College name -->
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-university text-muted"></i>
                </span>
              </div>
              <b-form-input id="hopeUniversity" class="form-control bg-white border-left-0 border-md"
                            v-model="mentee.hopeUniversity" placeholder="희망 대학(선택)" type="text"/>
            </div>
            <!-- Want Major -->
            <div class="input-group col-lg-12 mb-4">
              <div class="input-group-prepend">
                <span class="input-group-text bg-white px-4 border-md border-right-0">
                  <i class="fas fa-university text-muted"></i>
                </span>
              </div>
              <b-form-input id="hopeMajor" class="form-control bg-white border-left-0 border-md"
                            v-model="mentee.hopeMajor" placeholder="희망 학과(선택)" type="text"/>
            </div>
          </template>
          <!-- Submit Button -->
          <div class="form-group col-lg-12 mx-auto mb-0">
            <button id="frm_join" class="btn btn-primary btn-block py-2" @click="submitBtn">계정 생성</button>
          </div>
          <!-- Already Registered -->
          <div class="text-center w-100">
            <p class="text-muted font-weight-bold">
              이미 회원이신가요? <a class="text-primary ml-2" href="/member/login">Login</a>
            </p>
          </div>

        </div>
      </b-form-group>
    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "JoinForm",
  data() {
    return {
      errors: {},
      userType: '',
      rePassword: null,
      user: {
        userId: null,
        userName: null,
        password: null,
        email: null,
        gender: 'm',
        address: null,
        phone: null,
        userRole: 0,
      },
      mentor: {
        universityName: null,
        universityType: null,
        grade: null,
        major: null,
        wantDay: null,
        wantTime: null,
        requirement: null,
        history: null,
        accountNum: null,
        bankName: null,
      },
      mentee: {
        schoolName: null,
        major: null,
        grade: null,
        hopeUniversity: null,
        hopeMajor: null,
      },
      menteeMajors: [
        {value: null, text: '현재계열', disabled: true},
        {value: 'literature', text: '문과'},
        {value: 'science', text: '이과'},
        {value: 'art', text: '예체능'},
        {value: 'no', text: '미정'}
      ],
      gradeOptions: [
        {value: null, text: '현재학년', disabled: true},
        {value: 1, text: '1학년'},
        {value: 2, text: '2학년'},
        {value: 3, text: '3학년'},
        {value: 4, text: '홈스쿨링'},
      ],
      universityOptions: [
        { value: null, text: '학교 유형', disabled: true },
        { value: 'university', text: '대학교' },
        { value: 'college', text: '전문대' },
      ],
      mentorGradeOptions: [
        {value: null, text: '현재학년', disabled: true},
        {value: 1, text: '1학년'},
        {value: 2, text: '2학년'},
        {value: 3, text: '3학년'},
        {value: 4, text: '4학년'},
      ],
      majorOptions: [
        {value: null, text: '전공 계열', disabled: true},
        {value: "humanities", text: '인문계열'},
        {value: "education", text: '교육계열'},
        {value: "engineering", text: '공학계열'},
        {value: "society", text: '사회계열'},
        {value: "nature", text: '자연계열'},
        {value: "anp", text: '예체능계열'},
        {value: "medicine", text: '의약계열'},
      ],
      dayOptions: [
        {value: null, text: '희망 요일', disabled: true},
        {value: "mon", text: '월요일'},
        {value: "tue", text: '화요일'},
        {value: "wed", text: '수요일'},
        {value: "thu", text: '목요일'},
        {value: "fri", text: '금요일'},
        {value: "sat", text: '토요일'},
        {value: "sun", text: '일요일'},
        {value: "all", text: '무관'},
      ],
      timeOptions: [
        {value: null, text: '희망 시간', disabled: true},
        {value: "am", text: '오전(09:00 ~ 12:00)'},
        {value: "pm", text: '오후(12:00 ~ 17:00)'},
        {value: "evening", text: '저녁(17:00 ~ 21:00)'},
        {value: "all", text: '무관'},
      ],
      requireOptions: [
        {value: null, text: '원하는 진행방식', disabled: true},
        {value: "videoChat", text: '비대면(화상채팅)'},
        {value: "myTown", text: '멘토의 동네에서 대면 수업'},
        {value: "yourTown", text: '멘티의 동네에서 대면 수업'},
        {value: "rentalSpace", text: '카페나 스터디룸 대여희망'},
      ],
      checkId: null,


    }
  },
  computed: {
    phoneValidation() {
      if(this.user.phone === null || this.user.phone === '') {
        return null;
      }else if(this.user.phone.match('^\\d{9,11}$')){
        return true;
      }else {
        return false;
      }
    },
    nameValidation() {
      if(this.user.userName === null) {
        return null;
      }else if(this.user.userName.length !== 0){
        return true;
      }else {
        return false;
      }
    },
    pwValidation() {
      if(this.user.password === null) {
        return null;
      }else if(this.user.password.match('^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}$')){
        return true;
      }else {
        return false;
      }
    },
    rePwValidation() {
      if(this.rePassword === null) {
        return null;
      }else if(this.rePassword === this.user.password && this.rePassword !== ''){
        return true;
      }else {
        return false;
      }
    },
    mailValidation() {
      if(this.user.email === null) {
        return null;
      }else if(this.user.email.length !== 0){
        return true;
      }else {
        return false;
      }
    },
    addressValidation() {
      if(this.user.address === null) {
        return null;
      }else if(this.user.address.length !== 0){
        return true;
      }else {
        return false;
      }
    },
  },
  created() {
    this.userType = this.$route.params.type;
  },
  methods: {
    submitBtn(){
      if(this.nameValidation === null || this.nameValidation === false) {
        return;
      } else if (this.checkId === null || this.checkId === false) {
        return;
      } else if (this.pwValidation === null || this.pwValidation === false) {
        return;
      } else if (this.rePwValidation === null || this.rePwValidation === false) {
        return;
      } else if (this.mailValidation === null || this.mailValidation === false) {
        return;
      } else if (this.addressValidation === null || this.addressValidation === false) {
        return;
      } else if (this.phoneValidation === null || this.phoneValidation === false) {
        return;
      }
      let member = this.userType === 'mentor' ? {...this.user, ...this.mentor} : {...this.user, ...this.mentee, userRole: 1};
      axios.post('/member/join', member)
        .then(response => {
          console.log(response);
          this.$router.replace({
            name:"Index",
          })
        })
        .catch(error => {
          console.log(error);
        })
    },
    clickCheckId() {
      if(this.user.userId === null || this.user.userId === '') {
        return;
      }
      axios.get('/member/check-id', {params: {userId: this.user.userId}})
          .then(response => {
            if(response.data.empty){
              this.checkId = true;
              alert("사용가능한 아이디 입니다.")
            }else {
              this.checkId = false;
              alert("사용 불가능한 아이디 입니다.")
            }
          })
          .catch(error => {
            console.log(error);
          })

    }
  }

}
</script>

<style scoped>
@import '../../assets/css/member/join-form.css';

</style>