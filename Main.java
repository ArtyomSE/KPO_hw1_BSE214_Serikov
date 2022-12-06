import java.util.Scanner;

public class Main {
    /*
    В классе Main описан механизм игры "Реверси" в двух возможных режимах.
    В самом начале создаётся "доска", на которой будет идти игра, после чего происходит выбор режима и объявление
    переменных для номеров игроков или игрока и компьютера и хранения координат.
    В цикле while, пока доска полностью не заполнится фишками или пока не останется мест для фишек одного из игроков,
    последовательно ставятся фишки на выбранную / просчитанную позицию, а также выводятся доска и текущий счёт.
    После завершения работы цикла определяется победитель.
    "1" - обозначение фишек игрока, играющего чёрными фишками, а "2" - играющего белыми. "3" - возможные ходы;
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please, choose the game mode: 'player-computer' / 'player-player'");
        String mode_ = in.next();
        if (mode_.equals("player-player") || mode_.equals("player-computer")) {
            Board board_ = new Board();
            String str_;
            char symbol_;
            int pos_x_, pos_y_, player_num_ = 1, opponent_num_ = player_num_ % 2 + 1, stop_ = 0;
            while (!board_.is_full()) {
                board_.print_board();
                board_.count_score();
                if (board_.evaluate(player_num_) == -11) {
                    break;
                }
                //board_.highlight(player_num_);
                System.out.print("Enter the coordinates of the chosen cell: ");
                //board_.clear();
                str_ = in.next();
                pos_x_ = in.nextInt();
                symbol_ = str_.charAt(0);
                pos_y_ = symbol_ - 97;
                pos_x_ = 8 - pos_x_;
                if (board_.move_to_cell(pos_x_, pos_y_, player_num_)) {
                    if (mode_.equals("player-computer")) {
                        board_.print_board();
                        board_.count_score();
                        int coords_ = board_.evaluate(opponent_num_);
                        if (coords_ != -11) {
                            board_.move_to_cell(coords_ / 10, coords_ % 10, 2);
                            stop_ = 0;
                        } else {
                            System.out.println("No possible moves :(");
                            stop_++;
                        }
                    } else {
                        player_num_ = player_num_ % 2 + 1;
                        opponent_num_ = player_num_ % 2 + 1;
                    }
                }
                if (stop_ == 2) {
                    break;
                }
            }
            int won_ = board_.define_winner();
            if (won_ == 0) {
                System.out.println("Draw");
            } else {
                if (won_ == -1) {
                    System.out.println("White won!");
                } else if (won_ == 1) {
                    System.out.println("Black won!");
                }
            }
        } else {
            System.out.println("Invalid mode!");
        }
    }
}