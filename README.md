#SPRING BOOT + GRAPHQL + MONGODB + DGS NETFLIX


mutation newAuthor{ createAuthor(author:{ firstName:"long" lastName:"tran" }){ id firstName lastName } }

mutation newBook{ newBook(book:{ name:"long" page:1 authorId:"629ef9e277fb0b0019fe2016" }){ id name page author{ id firstName lastName } } }

query findAllBook{ findAllBook{ id name page author { id } } } mutation deleteBook{ deleteBook(id:"629efe4e77fb0b0019fe2017") } mutation updateBook{ updateBook(id:"629efe9e77fb0b0019fe2018",name:"nguu",page:123) { id name page } }
