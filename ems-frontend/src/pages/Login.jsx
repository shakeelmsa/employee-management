import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { loginUser } from '../services/authService'
import './Login.css'

const Login = () => {

  const navigate = useNavigate()

  const [loginData, setLoginData] = useState({
    email: '',
    password: ''
  })

  const handleChange = (e) => {

    setLoginData({
      ...loginData,
      [e.target.name]: e.target.value
    })
  }

 

  const handleSubmit = async (e) => {

   e.preventDefault()

   try {

      
      const response = await loginUser(loginData)

      console.log(response.data)

      // Extract Token

      const token = response.data.token

      // Store Token

      localStorage.setItem(
      "token",
      token
      )



    alert("Login Successful")

    navigate('/dashboard')

   } catch (error) {

      console.error(error)

      alert("Invalid Credentials")
   }
}

  return (

    <div className="login-container">

      <div className="login-card">

        <h2 className="login-title">
          Employee Login
        </h2>

        <form
          className="login-form"
          onSubmit={handleSubmit}
        >

          <input
            type="email"
            name="email"
            placeholder="Enter Email"
            className="login-input"
            value={loginData.email}
            onChange={handleChange}
          />

          <input
            type="password"
            name="password"
            placeholder="Enter Password"
            className="login-input"
            value={loginData.password}
            onChange={handleChange}
          />

          <button
            type="submit"
            className="login-button"
          >
            Login
          </button>

        </form>

        <p className="register-link">
          Don't have an account?
          <Link to="/"> Register</Link>
        </p>

      </div>

    </div>
  )
}

export default Login