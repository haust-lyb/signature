import axios from "axios";

export default {
    submit(params){
        return axios.post("/api/submit",params)
    }
}
