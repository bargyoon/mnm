<template>
  <div class="content_section">
    <div id="contentHeader">
      <div id="contentTitle">
        <p>멘토 게시판</p>
      </div>
    </div>
    <div id="contentBody">
      <hr>
      <b-row>
        <b-col cols="1" class="text-end">제목:</b-col>
        <b-col cols="5" style="font-size: 17px; font-weight: bolder;">{{ board.bdTitle }}</b-col>
        <b-col cols="3">
          <b-icon icon="clock"></b-icon>
          {{ board.regDate }}
        </b-col>
        <b-col>작성자: {{ board.member.userIdx }}</b-col>
        <b-col><span class="fa fa-thumbs-up mr-1"></span>{{ board.recCount }} |
          <b-icon icon="eye-fill"></b-icon>
          {{ board.viewCount }}
        </b-col>
      </b-row>
      <hr>
      <div id="contentBox">
        <div>
          {{ board.bdContent }}
        </div>
      </div>
      <div class='info file_info'>
        <ol>
          <li v-for="file in files" v-bind:key="file.flIdx" id="liTag">
            <a @click="downloadFile(file)">{{ file.originFileName }}</a><i class="fas fa-paperclip" id="fileClip"></i>
          </li>
        </ol>
      </div>
      <div id="otherUtil">
        <a @click="recommendBoard()">추천</a>
        <a id="shareURL" @click="clip();">공유</a>
      </div>
      <div class="content_footer">
        <button id="returnButton" type="button" onclick="location.href='/board/mentor'">목록</button>
      </div>
      <div class="wrap_comment">
        <textarea id="commentBox" required="required" maxlength="100" name="coComment"
                  v-model="commentValue"></textarea>
        <button class="writeButton" @click="clickComment()">등록</button>
      </div>
      <hr>
    </div>
    <div class="content_footer">
      <div id="wrapButton">
        <button type="button" @click="clickModify(board.bdIdx)">수정</button>
        <button type="button" @click="clickDelete(board.bdIdx)">삭제</button>
      </div>
    </div>
    <div class="container bootdey" style="font-size: 1rem">
      <div class="col-md-12 bootstrap snippets">
        <div class="panel">
          <div class="panel-body">
            <!-- Newsfeed Content -->
            <!--===================================================-->
            <div class="media-block">
              <div class="media-body">
                <hr>
                <!-- Comments -->
                <div>
                  <div v-for="(comment,index) in comments" v-bind:key="index">
                    <template v-if="comment.cmType == 0">
                      <div class="media-block">
                        <div :class="`media-body originInput`+index">
                          <div class="mar-btm">
                      <span
                          class="text-semibold text-success media-heading box-inline">{{
                          comment.member.userIdx
                        }}</span>
                            <b-dropdown style="display: inline-block; float: right;" variant="outline-secondary"
                                        size="sm" no-caret>
                              <template #button-content>
                                <span class="">...</span>
                              </template>
                              <b-dropdown-item @click="updateCommentBtn(index)">수정</b-dropdown-item>
                              <b-dropdown-item @click="deleteComment(comment.coIdx)">삭제</b-dropdown-item>
                            </b-dropdown>
                          </div>
                          <p>{{ comment.coContent }}</p>
                          <div class="pad-ver">
                            <span class="text-muted text-sm"><span
                                pattern="yyyy-MM-dd hh:mm:ss"
                                value="${comment.regDate}"/></span>
                            <button type="button" class="newBtn"
                                    @click="addCommentBtn(index)">댓글달기
                            </button>
                            <a class="btn btn-default btn-hover-success active"
                               style="float: right"
                               @click="recommendComment(comment.coIdx)"> <i
                                class="fa fa-thumbs-up mr-1"></i>{{ comment.recCount }}
                            </a>
                          </div>
                        </div>
                        <div :class="`media-body hiddenInput`+index"
                             style="display: none">
                          <div class="mar-btm">
                            <span class="text-semibold">유저네임</span>
                            <a type="button" class="btn-close" aria-label="Close" style="float:right;"
                               @click="cancleUpdate(index)"></a>
                          </div>
                          <div class="bt_wrap mt-1">
                            <input type="text" placeholder="댓글을 남겨주세요"
                                   required v-model="comment.coContent"
                                   style="width: 85%; height: 50px; font-size: 16px;">
                            <button class="on writeButton"
                                    @click="updateComment(index, comment.coIdx)">등록
                            </button>
                          </div>
                        </div>
                        <hr>
                        <div :id="`input`+index" style="display: none">
                          <div class="media-block" style="position: relative;">
                            <span style="position: absolute; top: 32px">ㄴ</span>
                            <div class="media-body" style="padding: 30px;">
                              <div class="mar-btm">
                                <span class="text-semibold">사용자이름</span>
                              </div>
                              <div class="bt_wrap mt-1">
                                <input v-model="reComment[index]" type="text" placeholder="댓글을 남겨주세요"
                                       name="cmContent" id="cmContent" required
                                       style="width: 85%; height: 50px; font-size: 16px;">
                                <button class="on writeButton"
                                        @click="clickComment(index,comment.coIdx)">등록
                                </button>
                              </div>
                            </div>
                          </div>
                          <hr>
                        </div>
                      </div>
                    </template>
                    <div v-for="(chComment, index) in comments" v-bind:key="index">
                      <template v-if="chComment.cmType == 1 && chComment.prIdx == comment.coIdx">
                        <div class="media-block">
                          <span style="position: absolute;">ㄴ</span>
                          <div :class="`media-body originInput`+index" style="padding: 0 30px;">
                            <div class="mar-btm">
																	<span
                                      class="text-semibold text-success media-heading box-inline">{{
                                      chComment.member.userIdx
                                    }}</span>
                              <b-dropdown style="display: inline-block; float: right;" variant="outline-secondary"
                                          size="sm" no-caret>
                                <template #button-content>
                                  <span class="">...</span>
                                </template>
                                <b-dropdown-item @click="updateComment(index)">수정</b-dropdown-item>
                                <b-dropdown-item><a @click="deleteComment(chComment.coIdx)">삭제</a></b-dropdown-item>
                              </b-dropdown>
                            </div>
                            <p>{{ chComment.coContent }}</p>
                            <div class="pad-ver">
                          <span class="text-muted text-sm"><span
                              pattern="yyyy-MM-dd hh:mm:ss"
                              value="${chcomment.regDate}"></span></span>
                              <a
                                  class="btn btn-default btn-hover-success active"
                                  style="float: right"
                                  @click="recommendComment(chComment.coIdx)"> <i
                                  class="fa fa-thumbs-up mr-1"></i>{{ chComment.recCount }}
                              </a>
                            </div>
                          </div>
                          <div :class="`media-body hiddenInput`+index"
                               style="display: none;padding: 30px;">

                            <div class="mar-btm">
                              <span class="text-semibold">유저네임</span>
                              <a type="button" class="btn-close" aria-label="Close" style="float:right;"
                                 @click="cancleUpdate(index)"></a>
                            </div>

                            <div class="bt_wrap mt-1">
                              <input type="text" placeholder="댓글을 남겨주세요"
                                     name="cmContent" id="cmContent" required
                                     v-model="chComment.coContent"
                                     style="width: 85%; height: 50px; font-size: 16px;">
                              <button class="on writeButton"
                                      @click="updateComment(index, chComment.coIdx)">등록
                              </button>
                            </div>
                          </div>
                        </div>
                        <hr>
                      </template>
                    </div>
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
import dayjs from "dayjs";

export default {
  name: "BoardDetail",
  data() {
    return {
      boardIdx: '',
      board: {},
      files: [],
      commentValue: '',
      comments: [],
      reComment: [],
      originComment: [],
    }
  },
  created() {
    this.init();
  },
  methods: {
    async init() {
      this.boardIdx = this.$route.params.id;
      await axios.get('/board/detail/' + this.boardIdx)
          .then(response => {
            console.log(response.data);
            this.board = response.data.board;
            this.files = response.data.files;
            this.files.map(item => {
              item.regDate = null;
            })
            this.comments = response.data.board.boardComment;
            this.board.regDate = dayjs(this.board.regDate).format('YYYY-MM-DD HH:mm:ss');
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
            this.$router.replace({
              name: "MentorBoard",
            })
          });
    },
    clip() {
      let url = '';
      let textarea = document.createElement("textarea");
      document.body.appendChild(textarea);
      url = window.document.location.href;
      textarea.value = url;
      textarea.select();
      document.execCommand("copy");
      document.body.removeChild(textarea);
      alert("URL이 복사되었습니다.")
    },
    recommendBoard() {
      axios.put('/board/recommendBoard/'+this.boardIdx)
          .then(response => {
            console.log(response);
            this.init();
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
          });

    },
    recommendComment(idx) {
      axios.put('/board/recommendComment/'+idx)
          .then(response => {
            console.log(response);
            this.init();
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
          });

    },
    downloadFile(file) {
      axios.get('/download', {params: file, responseType: 'blob'})
          .then(response => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', file.originFileName);
            document.body.appendChild(link);
            link.click();
            console.log(response);
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
          });

    },
    clickModify(idx) {
      this.$router.push({
        name: "BoardModify",
        params: {id: idx},
      })
    },
    clickDelete(idx) {
      let flg = confirm("정말 삭제하시겠습니까?")
      if (flg) {
        axios.delete('/board/delete/' + idx)
            .then(response => {
              console.log(response);
              this.$router.replace({
                name: "MentorBoard",
              })
            })
            .catch(error => {
              alert('error!!');
              console.log(error)
            });
      }
    },
    clickComment(idx, prIdx) {
      let param = {};
      if(idx !== undefined) {
        param = {coContent: this.reComment[idx], prIdx: prIdx}
      } else {
        param = {coContent: this.commentValue}
      }
      axios.post('/board/comment/' + this.boardIdx, param)
          .then(response => {
            console.log(response);
            this.$router.go();
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
          });
    },
    addCommentBtn(idx) {
      let temp = document.querySelector('#input' + idx);
      if (temp.style.display == "none") {
        temp.style.display = ""
      } else {
        temp.style.display = "none"
      }
    },
    updateCommentBtn(idx) {
      this.originComment[idx] = this.comments[idx].coContent;
      console.log(this.originComment);
      document.querySelector('.originInput' + idx).style.display = "none";
      document.querySelector('.hiddenInput' + idx).style.display = "";
    },
    cancleUpdate(idx) {
      this.comments[idx].coContent = this.originComment[idx];
      document.querySelector('.originInput' + idx).style.display = ""
      document.querySelector('.hiddenInput' + idx).style.display = "none"
    },
    updateComment(idx) {
      axios.put('/board/modifyComment', this.comments[idx])
          .then(response => {
            console.log(response);
            this.$router.go();
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
          });
    },
    deleteComment(coIdx) {
      let flg = confirm("정말 삭제하시겠습니까?");
      if (flg) {
        return axios.delete('/board/deleteComment/' + coIdx)
            .then(response => {
              console.log(response);
              this.$router.go();
            })
            .catch(error => {
              alert('error!!');
              console.log(error)
            });
      }
    }
  }
}
</script>

<style scoped>

@import '../../assets/css/board/board-content.css';

.media-block {
  min-height: 105px;
}
</style>