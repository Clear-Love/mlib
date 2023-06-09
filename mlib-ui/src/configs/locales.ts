import enMessages from "../locales/en";
import zhMessages from "../locales/zh";
import jaMessages from "../locales/ja";

const supported = ["en", "zh", "ja"];
let locale = "zh";

try {
  // get browser default language
  const { 0: browserLang } = navigator.language.split("-");

  if (supported.includes(browserLang)) locale = browserLang;
} catch (e) {
  console.log(e);
}

export default {
  // current locale
  locale,

  // when translation is not available fallback to that locale
  fallbackLocale: "en",

  // availabled locales for user selection
  availableLocales: [
    {
      code: "en",
      flag: "us",
      name: "united-states",
      label: "English",
      messages: enMessages,
    },
    {
      code: "zh",
      flag: "cn",
      name: "china",
      label: "中文",
      messages: zhMessages,
    },
    {
      code: "ja",
      flag: "jp",
      name: "japan",
      label: "日本語",
      messages: jaMessages,
    },
  ],
  messages: {
    en: enMessages,
    zh: zhMessages,
    ja: jaMessages,
  },
};
