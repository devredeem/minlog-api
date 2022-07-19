<script setup lang="ts">
import {defineProps, onMounted, ref} from "vue";
import axios from 'axios';
import {useRouter} from "vue-router";

const props = defineProps({
  postId: {
    type: [Number, String],
    require: true,
  },
});

const post = ref({
  id: 0,
  title: "",
  content: "",
});

const router = useRouter();

const clickToDelete = function() {
  axios.delete(`/api/posts/${props.postId}`)
    .then(() => {
      alert("게시글이 삭제되었습니다.")
      router.replace({ name: "home" });
  });
}

const moveToEdit = () => {
  router.push({name: "edit", params: { postId: props.postId } });
}

onMounted(() => {
  axios.get(`/api/posts/${props.postId}`).then((response) => {
    post.value = response.data;
  });
});
</script>

<template>
  <el-row>
    <el-col>
      <h2 class="title">{{ post.title }}</h2>

      <div class="sub d-flex">
        <div class="category">개발</div>
        <div class="regDate">2022-07-15</div>
      </div>
    </el-col>
  </el-row>

  <el-row class="mt-3">
    <el-col>
      <div class="content">{{ post.content }}</div>
    </el-col>
  </el-row>

  <el-row class="mt-3">
    <el-col>
      <div class="d-flex justify-content-end">
        <el-button type="danger" @click="clickToDelete()">삭제</el-button>
        <el-button type="warning" @click="moveToEdit()">수정</el-button>
      </div>
    </el-col>
  </el-row>
</template>

<style scoped lang="scss">
.title {
  font-size: 1.6rem;
  font-weight: 600;
  color: #343434;
  margin: 0;
}

.sub {
  margin-top: 6px;
  font-size: 0.75rem;

  .regDate{
    margin-left: 10px;
    color: 646464;
  }
}

.content {
  font-size: 1rem;
  margin-top: 11px;
  color: #646464;
  white-space: break-spaces;
  line-height: 1.5;
}
</style>