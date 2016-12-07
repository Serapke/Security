import bcrypt


class SolutionBCrypt:

    INPUT_FILE = '../rockyou-samples.bcrypt.txt'
    OUTPUT_FILE = '../bcrypt-lines.txt'

    OUTPUT_LIMIT = 5
    PASSWORD = b"123456"

    @staticmethod
    def crack():
        hashes = SolutionBCrypt().read_input()
        lines = []
        count = 0
        line = 1

        for h in hashes:
            if count == SolutionBCrypt.OUTPUT_LIMIT:
                break
            if bcrypt.checkpw(SolutionBCrypt.PASSWORD, h.encode('utf-8')):
                lines.append(line)
                count += 1
            line += 1
        return lines

    @staticmethod
    def read_input():
        with open(SolutionBCrypt.INPUT_FILE) as f:
            hashes = f.readlines()
        return hashes

    @staticmethod
    def print_output(output):
        f = open(SolutionBCrypt.OUTPUT_FILE, 'w')
        for line in output:
            f.write(str(line) + '\n')

output = SolutionBCrypt().crack()
SolutionBCrypt().print_output(output)
