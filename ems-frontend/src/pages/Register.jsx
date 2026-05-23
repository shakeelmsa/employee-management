import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import axios from 'axios'
import { registerUser } from '../services/authService'
import './Register.css'

const Register = () => {

  const navigate = useNavigate()

  const [employee, setEmployee] = useState({
   firstName: '',
    lastName: '',
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


  const handleSubmit = async (e) => {

  e.preventDefault()

  try {

    const response = await registerUser(employee)

    console.log(response.data)

    alert("Registration Successful")

    navigate('/login')

  } catch (error) {

    if(error.response.status === 409){

      alert("Email already exists")
   }

    alert("Registration Failed")
  }
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
       name="firstName"
       placeholder="Enter First Name"
       className="register-input"
       value={employee.firstName}
       onChange={handleChange}
       />

       <input
       type="text"
       name="lastName"
       placeholder="Enter Last Name"
       className="register-input"
      value={employee.lastName}
       onChange={handleChange}
      />

          <input
            type="email"
            name="email"
            placeholder="Enter Email"
            className="register-input"
            value={employee.email}
            onChange={handleChange}
            autoComplete="off"
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
            autoComplete="new-password"

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