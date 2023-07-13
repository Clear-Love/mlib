<template v-slot="{search}">
  <v-container fluid>
    <v-row>
      <v-col cols="12" sm="6" lg="2" v-for="book in books" :key="book.bookId">
        <v-card max-width="300px">
          <v-img :src="book.coverImage" height="300"></v-img>
          <v-card-title>{{ book.title }}</v-card-title>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {post} from "@/utils/request";
import eventBus from "@/utils/eventBus";

export default {
  data() {
    return {
      books: [],
      page: 1,
      limit: 20,
      loading: false,
      hasMoreData: true,
      text: '三国',
    };
  },
  mounted() {
    eventBus.on('search', this.search);
  },
  created() {
    this.fetchBooks();
    window.addEventListener("scroll", this.handleScroll);
  },
  unmounted() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  methods: {
    fetchBooks() {
      if (this.loading || !this.hasMoreData) return;
      this.loading = true;
      // You can use axios or any other HTTP library to make the request
      post('/api/book/search', {
        text: this.text,
        page: this.page,
        limit: this.limit,
      }, (message, status, data) => {
        this.books = this.books.concat(data.records);
        // Check if there are more pages to load
        this.hasMoreData = data.pages > this.page;
        this.page++;
        this.loading = false;
      })
    },
    search(text) {
      this.text = text
      post('/api/book/search', {
        text: this.text,
        page: this.page,
        limit: this.limit,
      }, (message, status, data) => {
        this.books = data.records;
        // Check if there are more pages to load
        this.hasMoreData = data.pages > this.page;
        this.page++;
        this.loading = false;
      })
    },
    handleScroll() {
      const scrollHeight = document.documentElement.scrollHeight;
      const scrollTop = document.documentElement.scrollTop;
      const clientHeight = document.documentElement.clientHeight;
      if (scrollTop + clientHeight >= scrollHeight && !this.loading) {
        this.fetchBooks();
      }
    },
  },
};
</script>
