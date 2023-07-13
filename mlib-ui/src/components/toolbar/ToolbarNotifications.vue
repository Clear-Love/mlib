<template>
  <v-menu location="bottom right" transition="slide-y-transition">
    <!-- ---------------------------------------------- -->
    <!-- Activator Btn -->
    <!-- ---------------------------------------------- -->
    <template v-slot:activator="{ props }">
      <v-btn icon v-bind="props" class="text-none">
        <v-icon>mdi-bell-outline</v-icon>
        <v-badge content="1" v-if="messageCnt>0" color="error" offset-x="-5" offset-y="-15"/>
      </v-btn>
    </template>
    <v-card min-width="270">
      <v-list elevation="1" lines="three" density="compact">
        <v-list-subheader>消息</v-list-subheader>
        <v-list-item v-for="(message, i) in messages" :key="i">
          <!-- ---------------------------------------------- -->
          <!-- Prepend-->
          <!-- ---------------------------------------------- -->
          <template v-slot:prepend>
            <v-avatar size="40" :color="message.color">
              <v-icon color="white">{{ message.icon }}</v-icon>
            </v-avatar>
          </template>
          <!-- ---------------------------------------------- -->
          <!-- Append-->
          <!-- ---------------------------------------------- -->
          <template v-slot:append>
            <div class="full-h d-flex align-center">
              <span class="text-body-2 text-grey"> {{ message.time }}</span>
            </div>
          </template>
          <!-- ---------------------------------------------- -->
          <!-- Main Content-->
          <!-- ---------------------------------------------- -->
          <div>
            <v-list-item-title class="font-weight-bold text-primary">{{
                message.title
              }}</v-list-item-title>
            <v-list-item-subtitle>{{ message.subtitle }}</v-list-item-subtitle>
          </div>
        </v-list-item>
      </v-list>
    </v-card>

  </v-menu>
</template>

<script lang="ts">
export default {
  data: () => ({
    messages: [
      {
        title: "回复我的",
        color: "primary",
        icon: "mdi-message-processing",
        subtitle: '',
        time: "3 min",
      },
      {
        title: "@我的",
        color: "success",
        icon: "mdi-email-outline",
        subtitle: '',
        time: "3 min",
      },
      {
        title: "系统消息",
        color: "teal lighten-1",
        icon: "mdi-cog",
        subtitle: '',
        time: "4 min",
      },
      {
        title: "我的消息",
        color: "teal accent-3",
        icon: "mdi-chat-processing",
        subtitle: '',
        time: "3 hr",
      },
    ],
    bl: true,
    messageCnt: 1
  })
}

</script>

<style scoped lang="scss">

</style>
