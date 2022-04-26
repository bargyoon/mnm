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
      <b-row>
        <b-col class="col-sm-2">
        </b-col>
        <b-col>
          <ol>
            <li v-for="file in importFiles" v-bind:key="file.flIDx">{{ file.originFileName}} <b-icon icon="x" @click="deleteFile(file.flIdx)"></b-icon></li>
          </ol>
        </b-col>
      </b-row>
      <hr class="dropdown-divider mb-4">
      <b-row>
        <b-form-group label-cols="4" label-cols-lg="2" label="내용" label-for="input-content">
          <b-form-textarea id="input-content" rows="8" v-model="board.bdContent"></b-form-textarea>
        </b-form-group>
      </b-row>
    </b-card>
    <br>
    <div style="display: flex; justify-content: center">
      <template v-if="type === 'form'">
        <b-button variant="success" @click="saveBoard">등록하기</b-button>
      </template>
      <template v-else>
        <b-button variant="success" @click="modifyBoard">수정하기</b-button>
      </template>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardForm",
  components: {

  },
  data() {
    return {
      type: '',
      board: {
        bdTitle: null,
        bdContent: null,
      },
      importFiles: [],
      files: [],
      deleteFiles: [],
    }
  },
  created() {
    if (this.$route.name == 'BoardForm') {
      this.type = 'form';
    } else {
      this.type = 'modify';
      this.init();
    }
  },
  methods: {
    async init() {
      this.boardIdx = this.$route.params.id;
      await axios.get('/board/detail/'+ this.boardIdx)
          .then(response => {
            console.log(response);
            this.board = response.data.board;
            this.importFiles = response.data.files;
          })
          .catch(error => {
            console.log(error)
            location.replace('/board/mentor')
          });
    },
    deleteFile(idx) {
      for (let i = 0; i < this.importFiles.length; i++) {
        if(this.importFiles[i].flIdx === idx) {
          this.deleteFiles.push(idx);
          this.importFiles.splice(i,1);
        }
      }
    },
    saveBoard() {
      if (this.board.bdTitle === null || this.board.bdContent === null) {
        alert('제목이나 내용은 비어있으면 안됩니다.');
        return;
      }
      let formData = new FormData();
      for(let i = 0; i < this.files.length; i++){
        formData.append("files", this.files[i]);
      }
      formData.append('board', JSON.stringify(this.board));
      axios.post('/board/create',formData, {headers: {
        'Content-Type': 'multipart/form-data'
      }})
        .then(() => {
          this.$router.replace({
            name:"MentorBoard",
          })
        })
        .catch(error => {
          alert('error!!');
          console.log(error)
        });
    },
    modifyBoard() {
      if (this.title === null || this.content === null) {
        alert('제목이나 내용은 비어있으면 안됩니다.');
        return;
      }
      let formData = new FormData();
      for(let i = 0; i < this.files.length; i++){
        formData.append("files", this.files[i]);
      }
      formData.append('board', JSON.stringify(this.board));
      formData.append('deletedFiles', this.deleteFiles);
      axios.put('/board/modify',formData,{headers: {
        'Content-Type': 'multipart/form-data'
      }})
          .then(() => {
            this.$router.replace({
              name:"BoardDetail",
              params: {id: this.boardIdx},
            })
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