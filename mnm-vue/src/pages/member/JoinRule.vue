<template>
  <div class="container" style="padding-top: 100px;min-height:93vh">
    <div class="row align-items-center">
      <!-- For Demo Purpose -->


      <div style="display: flex; justify-content: center;">
        <div
            style="max-width: 600px;">
          <div style="padding: 22px;">

            <div>
              <h2>회원가입</h2>
              <p style="color: #343a40; font-size: 12px">저희 M&M의 회원이 되시면
                다양한 서비스를 이용하실 수 있습니다.</p>
            </div>
            <b-form-checkbox-group v-model="status">
              <b-card header-tag="header" class="mb-4">
                <template #header>
                  <b-form-checkbox value="1"><p>홈페이지 이용약관(필수)</p></b-form-checkbox>
                </template>
                <b-card-text text-tag="span">[앵커]<br> 삼성증권이 이른바 '유령주식' 매도 사태로 피해를 입은 개인투자자들에 대해 보상안을
                  발표했습니다.<br> 보상이 끝난 뒤에는 주식을 판 직원에 대해서 손해배상을 청구할 계획인데 보상과
                  손해배상 절차가 모두, 순탄치는 않을 것으로 보입니다. <br> 국현호 기자가 보도합니다.<br>
                  <br> [리포트]<br> 삼성증권의 이른바 '유령주식'이 처음 시장에 나온 건 6일 오전 9시
                  35분. 보상을 받을 수 있는 사람은 이 전에 삼성증권 주식을 갖고 있다 당일에 판 모든 개인투자잡니다.<br>
                  당일 장중 최고가인 3만 9천8백 원에서 판 가격 차이만큼을 보상하겠다는 게 삼성증권의 보상안입니다.<br>
                  예를 들어 삼성증권 주식을 100주 가진 사람이 당일 3만 7천 원에 모두 팔았다면, 1주에 최고가와의 차액
                  2천8백 원씩 모두 28만 원을 받게 됩니다. 당일 주식을 팔았다 더 비싸게 다시 산 사람도 가격 차이만큼 보상받게
                  됩니다.<br> 삼성은 매매 과정에 들어간 수수료와 세금까지 함께 보상하기로 했습니다.
                </b-card-text>
              </b-card>
              <b-card header-tag="header" class="mb-4">
                <template #header>
                  <b-form-checkbox value="2"><p>개인정보 처리 방침(필수)</p></b-form-checkbox>
                </template>
                <b-card-text text-tag="span">[앵커]<br> 삼성증권이 이른바 '유령주식' 매도 사태로 피해를 입은 개인투자자들에 대해 보상안을
                  발표했습니다.<br> 보상이 끝난 뒤에는 주식을 판 직원에 대해서 손해배상을 청구할 계획인데 보상과
                  손해배상 절차가 모두, 순탄치는 않을 것으로 보입니다. <br> 국현호 기자가 보도합니다.<br>
                  <br> [리포트]<br> 삼성증권의 이른바 '유령주식'이 처음 시장에 나온 건 6일 오전 9시
                  35분. 보상을 받을 수 있는 사람은 이 전에 삼성증권 주식을 갖고 있다 당일에 판 모든 개인투자잡니다.<br>
                  당일 장중 최고가인 3만 9천8백 원에서 판 가격 차이만큼을 보상하겠다는 게 삼성증권의 보상안입니다.<br>
                  예를 들어 삼성증권 주식을 100주 가진 사람이 당일 3만 7천 원에 모두 팔았다면, 1주에 최고가와의 차액
                  2천8백 원씩 모두 28만 원을 받게 됩니다. 당일 주식을 팔았다 더 비싸게 다시 산 사람도 가격 차이만큼 보상받게
                  됩니다.<br> 삼성은 매매 과정에 들어간 수수료와 세금까지 함께 보상하기로 했습니다.
                </b-card-text>
              </b-card>
              <b-card header-tag="header" class="mb-4">
                <template #header>
                  <b-form-checkbox value="3"><p>개인정보 처리의 위탁(필수)</p></b-form-checkbox>
                </template>
                <b-card-text text-tag="span">[앵커]<br> 삼성증권이 이른바 '유령주식' 매도 사태로 피해를 입은 개인투자자들에 대해 보상안을
                  발표했습니다.<br> 보상이 끝난 뒤에는 주식을 판 직원에 대해서 손해배상을 청구할 계획인데 보상과
                  손해배상 절차가 모두, 순탄치는 않을 것으로 보입니다. <br> 국현호 기자가 보도합니다.<br>
                  <br> [리포트]<br> 삼성증권의 이른바 '유령주식'이 처음 시장에 나온 건 6일 오전 9시
                  35분. 보상을 받을 수 있는 사람은 이 전에 삼성증권 주식을 갖고 있다 당일에 판 모든 개인투자잡니다.<br>
                  당일 장중 최고가인 3만 9천8백 원에서 판 가격 차이만큼을 보상하겠다는 게 삼성증권의 보상안입니다.<br>
                  예를 들어 삼성증권 주식을 100주 가진 사람이 당일 3만 7천 원에 모두 팔았다면, 1주에 최고가와의 차액
                  2천8백 원씩 모두 28만 원을 받게 됩니다. 당일 주식을 팔았다 더 비싸게 다시 산 사람도 가격 차이만큼 보상받게
                  됩니다.<br> 삼성은 매매 과정에 들어간 수수료와 세금까지 함께 보상하기로 했습니다.
                </b-card-text>
              </b-card>
            </b-form-checkbox-group>
            <div class="btn-wrapper">
              <button class="join-btn" @click="clickJoin('mentor')">멘토로 가입</button>
              <button class="join-btn" @click="clickJoin('mentee')">멘티로 가입</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "JoinRule",
  data() {
    return{
      status: [],
    }
  },
  methods: {
    clickJoin(type) {
      if(this.status.length < 3) {
        alert('필수 체크박스에 체크하셔야합니다.');
        return;
      }
      this.$router.push({
        name: 'JoinForm',
        params: {type: type},

      })
    }
  }
}
</script>

<style scoped>
@import '../../assets/css/member/join-rule.css';

.card-header {
  height: 40px;
}

.card-body {
  height: 120px;
  overflow: scroll;
}
</style>