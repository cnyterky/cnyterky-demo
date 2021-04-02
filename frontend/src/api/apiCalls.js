import axios from 'axios';
export const singUp = (body) => {
    return axios.post('/api/1.0/users', body);
}