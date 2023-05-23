import { defineStore } from "pinia";

type MessageType = "" | "info" | "success" | "error" | "warning";

export const useSnackbarStore = defineStore({
  id: "snackbarStore",
  state: () => ({
    isShow: false,
    message: "",
    type: "",
  }),
  persist: {
    enabled: true,
    strategies: [
      {
        storage: localStorage,
        paths: [""],
      }
    ],
  },

  getters: {},
  actions: {
    showMessage(message: string, type:MessageType) {
      this.isShow = true;
      this.message = message;
      this.type = type;
    },
  },
});
