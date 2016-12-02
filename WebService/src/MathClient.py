import suds # pip install suds

class MathClient:
    def __init__(self):
       self.client = suds.client.Client("http://localhost:9999/ws/marita?wsdl")
    
    def sum(self, a, b):
       return self.client.service.sum(a,b);

    def sub(self, a, b):
       return self.client.service.sub(a,b);
       
    def mult(self, a, b):
       return self.client.service.mult(a,b);
       
    def div(self, a, b):
       return self.client.service.div(a,b);              

    def fak(self, a):
       return self.client.service.fak(a);

    def fib(self, a):
       return self.client.service.fib(a);

    def ack(self, a, b):
       return self.client.service.ack(a,b);

if(__name__ == "__main__"):
    client = MathClient()
    print client.sum(6,3)
    print client.sub(19,2)
    print client.mult(6,3)
    print client.div(19,4)
    print client.fak(5)           
    print client.fib(8)
    print client.ack(3,2)    
