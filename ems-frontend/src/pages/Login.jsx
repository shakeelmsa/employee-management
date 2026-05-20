import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
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

  const handleSubmit = (e) => {

    e.preventDefault()

    console.log(loginData)

    // API Call Here

    alert("Login Successful")

    navigate('/dashboard')
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