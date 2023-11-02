
  mutation{
    createUser(input:{
           name: "John Doe",
     email: "john.doe@example.com"

    })
 
      {
      user {
        id
        name
        email
      }
      errors
    }
  }




....
