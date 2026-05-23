import axios from 'axios'

const BASE_URL =
    'http://localhost:8080/api/auth'

// REGISTER

export const registerUser = (employee) => {

    return axios.post(
        `${BASE_URL}/register`,
        employee
    )
}

// LOGIN

export const loginUser = async (loginData) => {

    const response = await axios.post(
        `${BASE_URL}/login`,
        loginData
    )

    // Store JWT Token

    localStorage.setItem(
        "token",
        response.data.token
    )

    return response
}

// LOGOUT

export const logoutUser = () => {

    localStorage.removeItem("token")
}

// GET TOKEN

export const getToken = () => {

    return localStorage.getItem("token")
}