service name = product
module = product
parent= neoServices
dependency with other module = no;

package= com.mehbub.product
Primary= --server.port: 8081
Secondary= --server.port=8018
application name= product
datasource url = jdbc:postgresql://localhost:5432/product
eureka client = yes
register-with-eureka: true