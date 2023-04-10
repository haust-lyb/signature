import axios from "axios";

export default {
    submit(params){
        return axios.post("/signature/api/submit",params)
    }
}
