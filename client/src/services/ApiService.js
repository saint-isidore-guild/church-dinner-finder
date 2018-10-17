import axios from 'axios'

const apiPrefix = 'http://localhost:8080'

export default {
    get(url) {
        return axios.get(`${apiPrefix}/${url}`).then((resp) => {
            return Promise.resolve(resp.data)
        })
        //     .catch((err) => {
        //     console.log('api GET error')
        //     console.log(err)
        // });
    },
    post(url, payload) {
        return axios.post(`${apiPrefix}/${url}`, payload).then((resp) => {
            return Promise.resolve(resp.data)
        })
        //     .catch((err) => {
        //     console.log('api POST error')
        //     console.log(err)
        // });
    }
}
