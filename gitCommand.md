Link : https://stackoverflow.com/questions/4470523/create-a-branch-in-git-from-another-branch
Want to create a new branch from any of the existing branches in Git. 

For example, if you have the following branches like:
- main
- f_microservice_comn_RestTemplate
- f_service_registry_eureka

Now, if I want to create a new branch called "f_service_registry_eureka" under the branch named "f_microservice_comn_RestTemplate", 
I will follow the below steps:

Step-0 :

Step-1 : Checkout or change into "f_microservice_comn_RestTemplate"
    $ git checkout f_microservice_comn_RestTemplate 

    - change to 'f_microservice_comn_RestTemplate' branch
Step-2 : Now create a new branch called "f_service_registry_eureka" under the "f_microservice_comn_RestTemplate" using the following command.
    $ git checkout -b f_service_registry_eureka f_microservice_comn_RestTemplate

    - create a branch called 'f_service_registry_eureka' from 'f_microservice_comn_RestTemplate'
    - The above will create a new branch called 'f_service_registry_eureka' under the branch 'f_microservice_comn_RestTemplate'. 
    - (note that 'branch1' in the above command isn't mandatory since the HEAD is currently pointing to it, 
    - We can be precise it if we are on a different branch though).
Step-3 : Switch to 'f_service_registry_eureka' branch
    $ git checkout f_service_registry_eureka

Step-3 : Now after working with the 'f_service_registry_eureka', we can commit and push or merge it locally or remotely.
    $ git push origin f_service_registry_eureka 

    - push the subbranch_of_b1 to remote