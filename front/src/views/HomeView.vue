<script setup lang="ts">
import axios from 'axios';
import {ref} from 'vue';
import {useRouter} from "vue-router";

const router = useRouter();

const posts = ref([]);

axios.get("/api/posts?page=1&size=5").then((response) => {
  response.data.forEach((r: any) => {
    posts.value.push(r);
  });
});
</script>

<template>
  <ul>
    <li v-for="post in posts" :key="post.id">
      <div class="title">
        <router-link :to="{ name: 'read', params: { postId: post.id } }">{{
          post.title
        }}</router-link>
      </div>

      <div class="content">
        {{ post.content }}
      </div>

      <div class="sub d-flex">
        <div class="category">개발</div>
        <div class="regDate">2022-07-15</div>
      </div>
    </li>
  </ul>
</template>

<style scoped lang="scss">
ul {
  list-style: none;
  padding: 0;

  li {
    margin-bottom: 2rem;

    .title {
    a {
      font-size: 1.2rem;
      color: #343434;
      text-decoration: none;
      }

      :hover {
        text-decoration: underline;
      }
    }

    .content {
      font-size: 0.9rem;
      margin-top: 8px;
      color: #646464;
    }

    &:last-child {
      margin-bottom: 0;
    }

    .sub {
      margin-top: 8px;
      font-size: 0.75rem;

      .regDate{
        margin-left: 10px;
        color: 646464;
      }
    }
  }
}
</style>