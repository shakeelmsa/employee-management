import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import './Register.css'

const Register = () => {

  const navigate = useNavigate()

  const [employee, setEmployee] = useState({
    name: '',
    email: '',
    phoneNumber: '',
    password: ''
  })

  const handleChange = (e) => {

    setEmployee({
      ...employee,
      [e.target.name]: e.target.value
    })
  }

  const handleSubmit = (e) => {

    e.preventDefault()

    console.log(employee)

    // API Call Here

    alert("Registration Successful")

    navigate('/login')
  }

  return (

    <div className="register-container">

      <div className="register-card">

        <h2 className="register-title">
          Employee Registration
        </h2>

        <form
          className="register-form"
          onSubmit={handleSubmit}
        >

          <input
            type="text"
            name="name"
            placeholder="Enter Name"
            className="register-input"
            value={employee.name}
            onChange={handleChange}
          />

          <input
            type="email"
            name="email"
            placeholder="Enter Email"
            className="register-input"
            value={employee.email}
            onChange={handleChange}
          />

          <input
            type="text"
            name="phoneNumber"
            placeholder="Enter Phone Number"
            className="register-input"
            value={employee.phoneNumber}
            onChange={handleChange}
          />

          <input
            type="password"
            name="password"
            placeholder="Enter Password"
            className="register-input"
            value={employee.password}
            onChange={handleChange}
          />

          <button
            type="submit"
            className="register-button"
          >
            Register
          </button>

        </form>

        <p className="login-link">
          Already have an account?
          <Link to="/login"> Login</Link>
        </p>

      </div>

    </div>
  )
}

export default Register