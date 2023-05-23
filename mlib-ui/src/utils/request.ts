import axios from 'axios'
import {useSnackbarStore} from "@/stores/sanckbarStore";

interface handler {
  (message:string, status:number):void
}

const err = () => useSnackbarStore().showMessage("发生了一些错误")

const defaultHandler = () => {}

function post(url:string, data:any, success:handler = defaultHandler, failure:handler = defaultHandler, error:()=>void = err){
  axios.post(url, data, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    withCredentials: true
  }).then(({data}) => {
    if(data.success) {
      success(data.message, data.status)
    }else {
      failure(data.message, data.status)
    }
  }).catch(error)
}

function get(url:string, success:handler = defaultHandler, failure:handler = defaultHandler, error:()=>void = err) {
  axios.get(url, {
    withCredentials: true
  }).then(({data}) => {
    if(data.success) {
      success(data.message, data.status)
    }else {
      failure(data.message, data.status)
    }
  }).catch(error)
}

export {get, post}
