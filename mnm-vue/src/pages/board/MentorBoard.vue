<template>
  <div class="board_section">
    <div id="boardHeader">
      <div id="boardTitle">
        <p>멘토 게시판</p>
      </div>
      <b-col lg="6" class="my-1">
        <b-form-group
            label="검색"
            label-for="filter-input"
            label-cols-sm="3"
            label-align-sm="right"
            label-size="sm"
            class="mb-0 wrap_search"
        >
          <b-input-group size="sm">
            <b-form-select
                id="initial-sort-select"
                v-model="searchType"
                :options="['제목', '작성자']"
            ></b-form-select>
            <b-form-input
                id="filter-input"
                v-model="searchWord"
                type="search"
                placeholder="검색할 내용을 입력하세요"
                class="w-50"
                trim
            ></b-form-input>

            <b-input-group-append>
              <b-button @click="searchKeyword">검색</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>
    </div>
    <hr>
    <b-table hover :items="items" :fields="fields" small head-variant="dark">
      <template #cell(index)="data">
        {{ data.index + 1 + (page - 1) * 10 }}
      </template>
      <template #cell(bdTitle)="data">
        <span @click="clickTitle(data.item.bdIdx)">{{ data.item.bdTitle }}</span>
      </template>
    </b-table>
    <hr>
    <div class="overflow-auto">
      <b-pagination v-model="page" :per-page="perPage" :total-rows="totalRow" @change="clickPage"
                    align="center"></b-pagination>
      <b-button variant="success" size="sm" style="position: absolute; right: 0; bottom: 13px;" href="/board/form">글
        작성
      </b-button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import dayjs from 'dayjs';

export default {
  name: "MentorBoard",
  data() {
    return {
      searchType: '제목',
      searchWord: '',
      filter: '',
      perPage: 0,
      totalRow: 0,
      page: 1,
      fields: [
        {
          key: 'index',
          label: 'no',
          sortable: true,
          thClass: 'w10',
        },
        {
          key: 'bdTitle',
          label: '제목',
          sortable: false,
          thClass: 'w40',
        },
        {
          key: 'author',
          label: '작성자',
          sortable: false,
          thClass: 'w10'
        },
        {
          key: 'regDate',
          label: '작성일',
          sortable: true,
          thClass: 'w20'
        },
        {
          key: 'viewCount',
          label: '조회수',
          sortable: true,
          thClass: 'w10'
        },
        {
          key: 'recCount',
          label: '추천수',
          sortable: true,
          thClass: 'w10'
        },
      ],
      items: [],
    }
  },
  created() {
    this.init();
  },
  methods: {
    async init() {
      await axios.get('/board/list', {params: {page: this.page - 1, size: 10, sort: "regDate,DESC",}})
          .then(response => {
            this.perPage = response.data.size;
            this.makeList(response);
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
          });
    },
    clickPage(page) {
      this.searchList(page);
    },
    async searchList(paramPage) {
      await axios.get('/board/searchList', {
        params: {
          page: paramPage - 1,
          size: 10,
          sort: "regDate,DESC",
          type: this.searchType === '제목' ? 'bdTitle' : 'name',
          keyword: this.searchWord
        }
      })
          .then(response => {
            this.makeList(response);
          })
          .catch(error => {
            alert('error!!');
            console.log(error)
          });
    },
    searchKeyword() {
      if (this.searchWord === '') {
        alert('검색어를 입력하세요.');
        return;
      }
      this.searchList();
    },
    clickTitle(idx) {
      this.$router.push({
        name: "BoardDetail",
        params: {id: idx},
      })
    },
    makeList(params) {
      this.items = params.data.content;
      this.totalRow = params.data.totalElements;
      this.items.map(item => {
        item.author = item.member.userIdx;
        item.regDate = dayjs(item.regDate).format('YYYY-MM-DD HH:mm:ss');
      })
    },
  }
}
</script>

<style>
@import '../../assets/css/board/board-main.css';

table.b-table thead th.w10 {
  width: 10%;
}

table.b-table thead th.w20 {
  width: 20%;
}

table.b-table thead th.w30 {
  width: 30%;
}

table.b-table thead th.w40 {
  width: 40%;
}

table.b-table thead th.w50 {
  width: 50%;
}

table.b-table thead th.w60 {
  width: 60%;
}

table.b-table thead th.w70 {
  width: 70%;
}

table.b-table thead th.w80 {
  width: 80%;
}

table.b-table thead th.w90 {
  width: 90%;
}

table.b-table thead th.w15 {
  width: 15%;
}

table.b-table thead th.w25 {
  width: 25%;
}

table.b-table thead th.w35 {
  width: 35%;
}

table.b-table thead th.w45 {
  width: 45%;
}

table.b-table thead th.w55 {
  width: 55%;
}

table.b-table thead th.w65 {
  width: 65%;
}

table.b-table thead th.w75 {
  width: 75%;
}

table.b-table thead th.w85 {
  width: 85%;
}

table.b-table thead th.w95 {
  width: 95%;
}

.page-link {
  color: black;
}

.page-item.active .page-link {
  background-color: #198754;
  border-color: #198754;
}



</style>