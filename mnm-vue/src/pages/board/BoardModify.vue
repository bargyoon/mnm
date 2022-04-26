<!-- boardForm 이랑 합침. 사용X -->
<template>
  <div class="board_section">
    <div id="boardHeader">
      <div id="boardTitle">
        <p>멘토 게시판</p>
      </div>
    </div>
    <hr>
    <b-card>
      <b-row>
        <b-form-group label-cols="4" label-cols-lg="2" label="제목" label-for="input-title">
          <b-form-input id="input-title" v-model="board.bdTitle"></b-form-input>
        </b-form-group>
      </b-row>
      <hr class="dropdown-divider mb-4">
      <b-row>
        <b-form-group label="첨부파일" label-cols-sm="2">
          <b-form-file id="file-default" v-model="files" multiple></b-form-file>
        </b-form-group>
      </b-row>
      <hr class="dropdown-divider mb-4">
      <b-row>
        <b-form-group label-cols="4" label-cols-lg="2" label="내용" label-for="input-content">
          <b-form-textarea id="input-content" rows="8" v-model="board.bdContent"></b-form-textarea>
        </b-form-group>
      </b-row>
    </b-card>
    <br>
    <div style="display: flex; justify-content: center"><b-button variant="success" @click="saveBoard">수정하기</b-button></div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardModify",
  data() {
    return {
      boardIdx: '',
      board: {},
      files: [],
    }
  },
  created() {
    this.init();
  },
  methods: {
    async init() {
      this.boardIdx = this.$route.params.id;
      await axios.get('/board/detail/'+ this.boardIdx)
          .then(response => {
            this.board = response.data
          })
          .catch(error => {
            console.log(error)
            location.replace('/board/mentor')
          });
    },
    saveBoard() {
      if (this.title === null || this.content === null) {
        alert('제목이나 내용은 비어있으면 안됩니다.');
        return;
      }

      axios.put('/board/modify',this.board)
          .then(response => {
            console.log(response);
            location.href='/board/mentor';
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
          });
    }
  },
}
</script>

<style scoped>
@import '../../assets/css/board/board-main.css';
</style>