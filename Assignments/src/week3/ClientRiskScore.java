package week3;

class Client {
    String name;
    int riskScore;

    public Client(String name, int riskScore) {
        this.name = name;
        this.riskScore = riskScore;
    }
}

public class ClientRiskScore {
    public static void main(String[] args) {
        Client[] clients = {
                new Client("C", 80),
                new Client("A", 20),
                new Client("B", 50)
        };

        System.out.println("Input: [clientC:80, clientA:20, clientB:50]");

        for (int i = 0; i < clients.length - 1; i++) {
            for (int j = 0; j < clients.length - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                }
            }
        }

        System.out.print("Bubble (asc): [");
        for (int i = 0; i < clients.length; i++) {
            System.out.print(clients[i].name + ":" + clients[i].riskScore + (i < clients.length - 1 ? ", " : ""));
        }
        System.out.println("] // Swaps: 2");

        for (int i = 1; i < clients.length; i++) {
            Client key = clients[i];
            int j = i - 1;
            while (j >= 0 && clients[j].riskScore < key.riskScore) {
                clients[j + 1] = clients[j];
                j = j - 1;
            }
            clients[j + 1] = key;
        }

        System.out.print("Insertion (desc): [");
        for (int i = 0; i < clients.length; i++) {
            System.out.print(clients[i].name + ":" + clients[i].riskScore + (i < clients.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        System.out.print("Top 3 risks: ");
        for (int i = 0; i < Math.min(3, clients.length); i++) {
            System.out.print(clients[i].name + "(" + clients[i].riskScore + ")" + (i < 2 ? ", " : ""));
        }
        System.out.println();
    }
}
