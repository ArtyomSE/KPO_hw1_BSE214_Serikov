public class Board {
    private final int[][] board_ = new int[8][8];

    /*
    В конструкторе Board создаётся модель стандартного игрового поля для игры "Реверси" на основе двумерного статического массива.
     */
    Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board_[i][j] = 0;
            }
        }
        board_[3][3] = 2;
        board_[3][4] = 1;
        board_[4][3] = 1;
        board_[4][4] = 2;
    }

    /*
    В методе is_full проверяется заполненность игрового поля.
     */
    public boolean is_full() {
        boolean full_ = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board_[i][j] == 0) {
                    full_ = false;
                    break;
                }
            }
        }
        return full_;
    }

    /*
    Метод count_score считает и выводит актуальный игровой счёт.
     */
    public void count_score() {
        int count_white_ = 0, count_black_ = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board_[i][j] == 2) {
                    count_white_++;
                } else if (board_[i][j] == 1) {
                    count_black_++;
                }
            }
        }
        System.out.println("White: " + count_white_ + " | " + "Black: " + count_black_);
        System.out.println();
    }

    /*
    Метод print_board передаёт на поток вывода текущее состояние игровой доски с подписанными строками и столбцами.
     */
    public void print_board() {
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + "|");
            for (int j = 0; j < 8; j++) {
                System.out.print(board_[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print("--");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            int symbol_num_ = 97 + i;
            char column_ = (char) symbol_num_;
            System.out.print(column_ + " ");
        }
        System.out.println();
        System.out.println();
    }

    /*
    Метод define_winner определяет победителя игры путём подсчёта и сравнения фишек двух цветов.
     */
    public int define_winner() {
        int count_white_ = 0, count_black_ = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board_[i][j] == 2) {
                    count_white_++;
                } else if (board_[i][j] == 1) {
                    count_black_++;
                }
            }
        }
        System.out.println("White: " + count_white_ + " | " + "Black: " + count_black_);
        System.out.println();
        if (count_black_ > count_white_) {
            return 1;
        } else if (count_black_ < count_white_) {
            return -1;
        } else {
            return 0;
        }
    }

    /*
    Метод move_to_cell ставит и перекрашивает фишки на поле в соответствии с правилами.
    Сначала входные даннные проверяются на корректность. Затем после установки фишки на выбранную корректную позицию
    метод рассматривает все возможные направление движения по полю от поставленной фишки и ищет цепочки фишек соперника,
    которые можно замкнуть, и перекрашивает их в случае обнаружения.
     */
    public boolean move_to_cell(int pos_x_, int pos_y_, int player_num_) {
        if (pos_x_ < 0 || pos_x_ > 7 || pos_y_ < 0 || pos_y_ > 7) {
            System.out.println("Invalid position on board!");
            System.out.println();
            return false;
        } else if (board_[pos_x_][pos_y_] != 0) {
            System.out.println("The cell is already taken!");
            System.out.println();
            return false;
        } else {
            int opponent_num_ = player_num_ % 2 + 1;
            board_[pos_x_][pos_y_] = player_num_;
            if (pos_x_ > 0 && pos_y_ > 0) {
                if (board_[pos_x_ - 1][pos_y_ - 1] == opponent_num_) {
                    int offset_ = 2;
                    while (pos_x_ - offset_ >= 0 && pos_y_ - offset_ >= 0) {
                        if (board_[pos_x_ - offset_][pos_y_ - offset_] == opponent_num_) {
                            offset_++;
                        } else if (board_[pos_x_ - offset_][pos_y_ - offset_] == player_num_) {
                            while (offset_ > 1) {
                                offset_--;
                                board_[pos_x_ - offset_][pos_y_ - offset_] = player_num_;
                            }
                            break;
                        } else if (board_[pos_x_ - offset_][pos_y_ - offset_] == 0) {
                            break;
                        }
                    }
                }
            }
            if (pos_x_ > 1) {
                if (board_[pos_x_ - 1][pos_y_] == opponent_num_) {
                    int offset_ = 2;
                    while (pos_x_ - offset_ >= 0) {
                        if (board_[pos_x_ - offset_][pos_y_] == opponent_num_) {
                            offset_++;
                        } else if (board_[pos_x_ - offset_][pos_y_] == player_num_) {
                            while (offset_ > 1) {
                                offset_--;
                                board_[pos_x_ - offset_][pos_y_] = player_num_;
                            }
                            break;
                        } else if (board_[pos_x_ - offset_][pos_y_] == 0) {
                            break;
                        }
                    }
                }
            }
            if (pos_x_ > 0 && pos_y_ < 7) {
                if (board_[pos_x_ - 1][pos_y_ + 1] == opponent_num_) {
                    int offset_ = 2;
                    while (pos_x_ - offset_ >= 0 && pos_y_ + offset_ <= 7) {
                        if (board_[pos_x_ - offset_][pos_y_ + offset_] == opponent_num_) {
                            offset_++;
                        } else if (board_[pos_x_ - offset_][pos_y_ + offset_] == player_num_) {
                            while (offset_ > 1) {
                                offset_--;
                                board_[pos_x_ - offset_][pos_y_ + offset_] = player_num_;
                            }
                            break;
                        } else if (board_[pos_x_ - offset_][pos_y_ + offset_] == 0) {
                            break;
                        }
                    }
                }
            }
            if (pos_y_ < 7) {
                if (board_[pos_x_][pos_y_ + 1] == opponent_num_) {
                    int offset_ = 2;
                    while (pos_y_ + offset_ <= 7) {
                        if (board_[pos_x_][pos_y_ + offset_] == opponent_num_) {
                            offset_++;
                        } else if (board_[pos_x_][pos_y_ + offset_] == player_num_) {
                            while (offset_ > 1) {
                                offset_--;
                                board_[pos_x_][pos_y_ + offset_] = player_num_;
                            }
                            break;
                        } else if (board_[pos_x_][pos_y_ + offset_] == 0) {
                            break;
                        }
                    }
                }
            }
            if (pos_x_ < 7 && pos_y_ < 7) {
                if (board_[pos_x_ + 1][pos_y_ + 1] == opponent_num_) {
                    int offset_ = 2;
                    while (pos_x_ + offset_ <= 7 && pos_y_ + offset_ <= 7) {
                        if (board_[pos_x_ + offset_][pos_y_ + offset_] == opponent_num_) {
                            offset_++;
                        } else if (board_[pos_x_ + offset_][pos_y_ + offset_] == player_num_) {
                            while (offset_ > 1) {
                                offset_--;
                                board_[pos_x_ + offset_][pos_y_ + offset_] = player_num_;
                            }
                            break;
                        } else if (board_[pos_x_ + offset_][pos_y_ + offset_] == 0) {
                            break;
                        }
                    }
                }
            }
            if (pos_x_ < 7) {
                if (board_[pos_x_ + 1][pos_y_] == opponent_num_) {
                    int offset_ = 2;
                    while (pos_x_ + offset_ <= 7) {
                        if (board_[pos_x_ + offset_][pos_y_] == opponent_num_) {
                            offset_++;
                        } else if (board_[pos_x_ + offset_][pos_y_] == player_num_) {
                            while (offset_ > 1) {
                                offset_--;
                                board_[pos_x_ + offset_][pos_y_] = player_num_;
                            }
                            break;
                        } else if (board_[pos_x_ + offset_][pos_y_] == 0) {
                            break;
                        }
                    }
                }
            }
            if (pos_x_ < 7 && pos_y_ > 0) {
                if (board_[pos_x_ + 1][pos_y_ - 1] == opponent_num_) {
                    int offset_ = 2;
                    while (pos_x_ + offset_ <= 7 && pos_y_ - offset_ >= 0) {
                        if (board_[pos_x_ + offset_][pos_y_ - offset_] == opponent_num_) {
                            offset_++;
                        } else if (board_[pos_x_ + offset_][pos_y_ - offset_] == player_num_) {
                            while (offset_ > 1) {
                                offset_--;
                                board_[pos_x_ + offset_][pos_y_ - offset_] = player_num_;
                            }
                            break;
                        } else if (board_[pos_x_ + offset_][pos_y_ - offset_] == 0) {
                            break;
                        }
                    }
                }
            }
            if (pos_y_ > 0) {
                if (board_[pos_x_][pos_y_ - 1] == opponent_num_) {
                    int offset_ = 2;
                    while (pos_y_ - offset_ >= 0) {
                        if (board_[pos_x_][pos_y_ - offset_] == opponent_num_) {
                            offset_++;
                        } else if (board_[pos_x_][pos_y_ - offset_] == player_num_) {
                            while (offset_ > 1) {
                                offset_--;
                                board_[pos_x_][pos_y_ - offset_] = player_num_;
                            }
                            break;
                        } else if (board_[pos_x_][pos_y_ - offset_] == 0) {
                            break;
                        }
                    }
                }
            }
            return true;
        }
    }

    /*
    Метод evaluate реализует оценочную функцию.
    Сначала метод ищет клетку цвета play_num_, а затем пробегает все возможные направления от этой клетки, считая
    по формуле ценность проходимых клеток. Если найденная последовательность замкнулась, то ценность возможной клетки
    записывается в соответственый элемент матрицы ценностей всех клеток.
     */
    int evaluate(int player_num_) {
        int opponent_num_ = player_num_ % 2 + 1;
        double[][] value_ = new double[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                value_[i][j] = -1;
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board_[i][j] == opponent_num_) {
                    if (i > 0 && j > 0) {
                        if (board_[i - 1][j - 1] == player_num_) {
                            int offset_ = 1;
                            while (i + offset_ <= 7 && j + offset_ <= 7) {
                                if (board_[i + offset_][j + offset_] == opponent_num_) {
                                    offset_++;
                                } else if (board_[i + offset_][j + offset_] == player_num_) {
                                    break;
                                } else {
                                    if (value_[i + offset_][j + offset_] == -1) {
                                        if (i + offset_ == 7 && j + offset_ == 7) {
                                            value_[i + offset_][j + offset_] = 0.8;
                                        } else if (i + offset_ == 7 || j + offset_ == 7) {
                                            value_[i + offset_][j + offset_] = 0.4;
                                        } else {
                                            value_[i + offset_][j + offset_] = 0;
                                        }
                                    }
                                    int index_i_ = i + offset_, index_j_ = j + offset_;
                                    while (offset_ > 0) {
                                        offset_--;
                                        if (i + offset_ == 7 || j + offset_ == 7) {
                                            value_[index_i_][index_j_] += 2;
                                        } else {
                                            value_[index_i_][index_j_] += 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (i > 0) {
                        if (board_[i - 1][j] == player_num_) {
                            int offset_ = 1;
                            while (i + offset_ <= 7) {
                                if (board_[i + offset_][j] == opponent_num_) {
                                    offset_++;
                                } else if (board_[i + offset_][j] == player_num_) {
                                    break;
                                } else {
                                    if (value_[i + offset_][j] == -1) {
                                        if (i + offset_ == 7 && (j == 0 || j == 7)) {
                                            value_[i + offset_][j] = 0.8;
                                        } else if (i + offset_ == 7) {
                                            value_[i + offset_][j] = 0.4;
                                        } else {
                                            value_[i + offset_][j] = 0;
                                        }
                                    }
                                    int index_ = i + offset_;
                                    while (offset_ > 0) {
                                        offset_--;
                                        if (i + offset_ == 7 || j == 0 || j == 7) {
                                            value_[index_][j] += 2;
                                        } else {
                                            value_[index_][j] += 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (i > 0 && j < 7) {
                        if (board_[i - 1][j + 1] == player_num_) {
                            int offset_ = 1;
                            while (i + offset_ <= 7 && j - offset_ >= 0) {
                                if (board_[i + offset_][j - offset_] == opponent_num_) {
                                    offset_++;
                                } else if (board_[i + offset_][j - offset_] == player_num_) {
                                    break;
                                } else {
                                    if (value_[i + offset_][j - offset_] == -1) {
                                        if (i + offset_ == 7 && j - offset_ == 0) {
                                            value_[i + offset_][j - offset_] = 0.8;
                                        } else if (i + offset_ == 7 || j - offset_ == 0) {
                                            value_[i + offset_][j - offset_] = 0.4;
                                        } else {
                                            value_[i + offset_][j - offset_] = 0;
                                        }
                                    }
                                    int index_i_ = i + offset_, index_j_ = j - offset_;
                                    while (offset_ > 0) {
                                        offset_--;
                                        if (i + offset_ == 7 || j - offset_ == 0) {
                                            value_[index_i_][index_j_] += 2;
                                        } else {
                                            value_[index_i_][index_j_] += 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (j < 7) {
                        if (board_[i][j + 1] == player_num_) {
                            int offset_ = 1;
                            while (j - offset_ >= 0) {
                                if (board_[i][j - offset_] == opponent_num_) {
                                    offset_++;
                                } else if (board_[i][j - offset_] == player_num_) {
                                    break;
                                } else {
                                    if (value_[i][j - offset_] == -1) {
                                        if (j - offset_ == 0 && (i == 0 || i == 7)) {
                                            value_[i][j - offset_] = 0.8;
                                        } else if (j - offset_ == 0) {
                                            value_[i][j - offset_] = 0.4;
                                        } else {
                                            value_[i][j - offset_] = 0;
                                        }
                                    }
                                    int index_ = j - offset_;
                                    while (offset_ > 0) {
                                        offset_--;
                                        if (j - offset_ == 0 || i == 0 || i == 7) {
                                            value_[i][index_] += 2;
                                        } else {
                                            value_[i][index_] += 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (i < 7 && j < 7) {
                        if (board_[i + 1][j + 1] == player_num_) {
                            int offset_ = 1;
                            while (i - offset_ >= 0 && j - offset_ >= 0) {
                                if (board_[i - offset_][j - offset_] == opponent_num_) {
                                    offset_++;
                                } else if (board_[i - offset_][j - offset_] == player_num_) {
                                    break;
                                } else {
                                    if (value_[i - offset_][j - offset_] == -1) {
                                        if (i - offset_ == 0 && j - offset_ == 0) {
                                            value_[i - offset_][j - offset_] = 0.8;
                                        } else if (i - offset_ == 0 || j - offset_ == 0) {
                                            value_[i - offset_][j - offset_] = 0.4;
                                        } else {
                                            value_[i - offset_][j - offset_] = 0;
                                        }
                                    }
                                    int index_i_ = i - offset_, index_j_ = j - offset_;
                                    while (offset_ > 0) {
                                        offset_--;
                                        if (i - offset_ == 0 || j - offset_ == 0) {
                                            value_[index_i_][index_j_] += 2;
                                        } else {
                                            value_[index_i_][index_j_] += 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (i < 7) {
                        if (board_[i + 1][j] == player_num_) {
                            int offset_ = 1;
                            while (i - offset_ >= 0) {
                                if (board_[i - offset_][j] == opponent_num_) {
                                    offset_++;
                                } else if (board_[i - offset_][j] == player_num_) {
                                    break;
                                } else {
                                    if (value_[i - offset_][j] == -1) {
                                        if (i - offset_ == 0 && (j == 0 || j == 7)) {
                                            value_[i - offset_][j] = 0.8;
                                        } else if (i - offset_ == 0) {
                                            value_[i - offset_][j] = 0.4;
                                        } else {
                                            value_[i - offset_][j] = 0;
                                        }
                                    }
                                    int index_ = i - offset_;
                                    while (offset_ > 0) {
                                        offset_--;
                                        if (i - offset_ == 0 || j == 0 || j == 7) {
                                            value_[index_][j] += 2;
                                        } else {
                                            value_[index_][j] += 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (i < 7 && j > 0) {
                        if (board_[i + 1][j - 1] == player_num_) {
                            int offset_ = 1;
                            while (i - offset_ >= 0 && j + offset_ <= 7) {
                                if (board_[i - offset_][j + offset_] == opponent_num_) {
                                    offset_++;
                                } else if (board_[i - offset_][j + offset_] == player_num_) {
                                    break;
                                } else {
                                    if (value_[i - offset_][j + offset_] == -1) {
                                        if (i - offset_ == 0 && j + offset_ == 7) {
                                            value_[i - offset_][j + offset_] = 0.8;
                                        } else if (i - offset_ == 0 || j + offset_ == 7) {
                                            value_[i - offset_][j + offset_] = 0.4;
                                        } else {
                                            value_[i - offset_][j + offset_] = 0;
                                        }
                                    }
                                    int index_i_ = i - offset_, index_j_ = j + offset_;
                                    while (offset_ > 0) {
                                        offset_--;
                                        if (i - offset_ == 0 || j + offset_ == 7) {
                                            value_[index_i_][index_j_] += 2;
                                        } else {
                                            value_[index_i_][index_j_] += 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (j > 0) {
                        if (board_[i][j - 1] == player_num_) {
                            int offset_ = 1;
                            while (j + offset_ <= 7) {
                                if (board_[i][j + offset_] == opponent_num_) {
                                    offset_++;
                                } else if (board_[i][j + offset_] == player_num_) {
                                    break;
                                } else {
                                    if (value_[i][j + offset_] == -1) {
                                        if (j + offset_ == 7 && (i == 0 || i == 7)) {
                                            value_[i][j + offset_] = 0.8;
                                        } else if (j + offset_ == 7) {
                                            value_[i][j + offset_] = 0.4;
                                        } else {
                                            value_[i][j + offset_] = 0;
                                        }
                                    }
                                    int index_ = j + offset_;
                                    while (offset_ > 0) {
                                        offset_--;
                                        if (j + offset_ == 7 || i == 0 || i == 7) {
                                            value_[i][index_] += 2;
                                        } else {
                                            value_[i][index_] += 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        double max_value_ = -1;
        int max_x_ = -1, max_y_ = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board_[i][j] == 3) {
                    board_[i][j] = 0;
                }
                if (value_[i][j] > max_value_) {
                    max_value_ = value_[i][j];
                    max_x_ = i;
                    max_y_ = j;
                }
            }
        }
        return 10 * max_x_ + max_y_;
    }

    /*
    Далее были реализованы методы highlight и clear для подсвечивания всех возможных ходов для игрока,
    но highlight не отлажен до конца.
     */
    /*public void highlight(int player_num_) {
        int opponent_num_ = player_num_ % 2 + 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board_[i][j] == player_num_) {
                    if (board_[i - 1][j - 1] == opponent_num_ && i > 0 && j > 0) {
                        int offset_ = 1;
                        while (i + offset_ <= 7 && j + offset_ <= 7) {
                            if (board_[i + offset_][j + offset_] == opponent_num_) {
                                offset_++;
                            } else if (board_[i + offset_][j + offset_] == player_num_) {
                                break;
                            } else if (board_[i + offset_][j + offset_] == 0) {
                                board_[i + offset_][j + offset_] = 3;
                                break;
                            }
                        }
                    }
                    if (board_[i - 1][j] == opponent_num_ && i > 0) {
                        int offset_ = 1;
                        while (i + offset_ <= 7) {
                            if (board_[i + offset_][j] == opponent_num_) {
                                offset_++;
                            } else if (board_[i + offset_][j] == player_num_) {
                                break;
                            } else if (board_[i + offset_][j] == 0) {
                                board_[i + offset_][j] = 3;
                                break;
                            }
                        }
                    }
                    if (board_[i - 1][j + 1] == opponent_num_ && i > 0 && j < 7) {
                        int offset_ = 1;
                        while (i + offset_ <= 7 && j - offset_ >= 0) {
                            if (board_[i + offset_][j - offset_] == opponent_num_) {
                                offset_++;
                            } else if (board_[i + offset_][j - offset_] == player_num_) {
                                break;
                            } else if (board_[i + offset_][j - offset_] == 0) {
                                board_[i + offset_][j - offset_] = 3;
                                break;
                            }
                        }
                    }
                    if (board_[i][j + 1] == opponent_num_ && j < 7) {
                        int offset_ = 1;
                        while (j - offset_ >= 0) {
                            if (board_[i][j - offset_] == opponent_num_) {
                                offset_++;
                            } else if (board_[i][j - offset_] == player_num_) {
                                break;
                            } else if (board_[i][j - offset_] == 0) {
                                board_[i][j - offset_] = 3;
                                break;
                            }
                        }
                    }
                    if (board_[i + 1][j + 1] == opponent_num_ && i < 7 && j < 7) {
                        int offset_ = 1;
                        while (i - offset_ >= 0 && j - offset_ >= 0) {
                            if (board_[i - offset_][j - offset_] == opponent_num_) {
                                offset_++;
                            } else if (board_[i - offset_][j - offset_] == player_num_) {
                                break;
                            } else if (board_[i - offset_][j - offset_] == 0) {
                                board_[i - offset_][j - offset_] = 3;
                                break;
                            }
                        }
                    }
                    if (board_[i + 1][j] == opponent_num_ && i < 7) {
                        int offset_ = 1;
                        while (i - offset_ >= 0) {
                            if (board_[i - offset_][j] == opponent_num_) {
                                offset_++;
                            } else if (board_[i - offset_][j] == player_num_) {
                                break;
                            } else if (board_[i - offset_][j] == 0) {
                                board_[i - offset_][j] = 3;
                                break;
                            }
                        }
                    }
                    if (board_[i + 1][j - 1] == opponent_num_ && i < 7 && j > 0) {
                        int offset_ = 1;
                        while (i - offset_ >= 0 && j + offset_ <= 7) {
                            if (board_[i - offset_][j + offset_] == opponent_num_) {
                                offset_++;
                            } else if (board_[i - offset_][j + offset_] == player_num_) {
                                break;
                            } else if (board_[i - offset_][j + offset_] == 0) {
                                board_[i - offset_][j + offset_] = 3;
                                break;
                            }
                        }
                    }
                    if (board_[i][j - 1] == opponent_num_ && j > 0) {
                        int offset_ = 1;
                        while (j + offset_ <= 7) {
                            if (board_[i][j + offset_] == opponent_num_) {
                                offset_++;
                            } else if (board_[i][j + offset_] == player_num_) {
                                break;
                            } else if (board_[i][j + offset_] == 0) {
                                board_[i][j + offset_] = 3;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void clear() {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                if (board_[i][j] == 3) {
                    board_[i][j] = 0;
                }
            }
        }
    }*/
}